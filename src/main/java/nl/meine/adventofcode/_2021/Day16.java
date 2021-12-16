package nl.meine.adventofcode._2021;

import com.google.common.base.Splitter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Day16 {

    Map<String, String> hexToBit = new HashMap<>();

    public Day16() {
        hexToBit.put("0", "0000");
        hexToBit.put("1", "0001");
        hexToBit.put("2", "0010");
        hexToBit.put("3", "0011");
        hexToBit.put("4", "0100");
        hexToBit.put("5", "0101");
        hexToBit.put("6", "0110");
        hexToBit.put("7", "0111");
        hexToBit.put("8", "1000");
        hexToBit.put("9", "1001");
        hexToBit.put("A", "1010");
        hexToBit.put("B", "1011");
        hexToBit.put("C", "1100");
        hexToBit.put("D", "1101");
        hexToBit.put("E", "1110");
        hexToBit.put("F", "1111");
    }

    public long one(String input) {
        String bitsString = hexToBits(input);
        Packet p = parse(bitsString);
        p.print(0);
        return sumVersions(p);
    }

    public int sumVersions(Packet p){
        int sum = 0;
        sum += p.version;
        for (Packet sub: p.subs) {
            sum += sumVersions(sub);
        }
        return sum;
    }

    public String hexToBits(String input) {
        return input.chars()
                .mapToObj(Character::toString)
                .map(s -> hexToBit.get(s)).collect(Collectors.joining());
    }

    public Packet parse(String s) {

        int version = getBitValue(s, 0, 3);
        int type = getBitValue(s,3,6);

        Packet p = new Packet(version, type);
        switch (type) {
            case 4:
                p.value = getLiteralValue(s,p);
                break;
            default:
                parseOperator(s,p);
                break;
        }
        return p;
    }

    public void parseOperator(String s, Packet p){
        String valuePart = s.substring(6);
        String lengthBit = ""+valuePart.charAt(0);
        if(lengthBit.equals("0")){
            parseLengthType0(valuePart, p);
        }else{
            parseLengthType1(valuePart, p);
        }
        p.lastIndex += 6;
    }

    public void parseLengthType1(String value, Packet p){
        int lengthIndex = 11;
        int numSubGroups = getBitValue(value, 1, lengthIndex+1);
        String subGroups = value.substring(12);
        p.lastIndex = lengthIndex+1;
        for (int i = 0; i < numSubGroups; i++) {
            Packet sub = parse(subGroups);
            subGroups = subGroups.substring(sub.lastIndex);
            p.lastIndex += sub.lastIndex;
            p.subs.add(sub);
        }
        int a = 0;
    }

    public void parseLengthType0(String value, Packet p){
        int lengthIndex = 16;
        int length = getBitValue(value, 1, lengthIndex);
        p.lastIndex = lengthIndex + length;
        String subGroups = value.substring(lengthIndex, lengthIndex + length);
        int remainingLength = length;
        Packet prev = null;
        int startIndex = 0;
        do {
            subGroups = subGroups.substring(startIndex);
            prev = parse(subGroups);
            p.subs.add(prev);
            startIndex = prev.lastIndex;
            remainingLength -= prev.lastIndex;
        }while(remainingLength > 0);
    }

    public String getLiteralValue(String s, Packet p) {
        String valuePart = s.substring(6);
        String val = parseGroups(valuePart, p);
        p.lastIndex += 6;

        return "" + Long.parseLong(val, 2);
    }

    public String parseGroups(String s, Packet p){
        String[] parts = StreamSupport.stream(Splitter.fixedLength(5).split(s).spliterator(), false).toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            String first = "" + part.charAt(0);
            String value = part.substring(1);
            sb.append(value);

            if (first.equals("0")) {
                p.lastIndex = (i+1)*5;
                break;
            }
        }
        return sb.toString();
    }

    public int getBitValue(String s, int from, int to){
        String versionBits = s.substring(from, to);
        return Integer.parseInt(versionBits, 2);
    }

    public long two(String input) {

        String bitsString = hexToBits(input);
        Packet p = parse(bitsString);

        return eval(p);
    }

    public long eval(Packet p){
        List<Packet> subs = p.subs;
        List<Long> values = subs.stream().map(this::evalToValuePackets).collect(Collectors.toList());
        switch(p.type){
            case 0:
                return values.stream().collect(Collectors.summingLong(Long::longValue));
            case 1:
                return values.stream().collect(Collectors.reducing((o, o2) -> o*o2)).get();
            case 2:
                return values.stream().min(Long::compareTo).get();
            case 3:
                return values.stream().max(Long::compareTo).get();
            case 5:
                return values.get(0) > values.get(1) ? 1: 0;
            case 6:
                return values.get(0) < values.get(1) ? 1: 0;
            case 7:
                return values.get(0).equals(values.get(1)) ? 1: 0;
        }
        return -1l;
    }

    public Long evalToValuePackets(Packet p){
        if(p.type ==4){
            return Long.valueOf(p.value);
        }else{
            return eval(p);
        }
    }

    public static void main(String[] args) throws IOException {
        Day16 d = new Day16();

        InputStream is = Day14.class.getClassLoader().getResourceAsStream("inputday16.txt");
        List<String> linesString = IOUtils.readLines(is, StandardCharsets.UTF_8);

        System.out.println("Numbers larger: " + d.two(linesString.get(0)));

    }
}

class Packet {

    int version;
    int type;

    String value = "";

    String rest;
    int lastIndex = -1;
    List<Packet> subs = new ArrayList<>();

    public Packet(int version, int type) {
        this.version = version;
        this.type = type;
    }

    public void print(int num){
        String pad = " ".repeat(num);
        System.out.print(pad +"[ version:" + value + ", type: "+ type);
        if(type == 4){
            System.out.print(", value: "+ value);
        }
        System.out.println(", subs: [");
        for (Packet s: subs) {
            s.print(num +2);
        }
        System.out.println(pad +"]");


    }
}
