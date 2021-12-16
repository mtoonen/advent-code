package nl.meine.adventofcode._2021;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    Day16 instance;
    String input;
    @BeforeEach
    void setUp() {
        instance = new Day16();
    }

    @Test
    void one() {
        input = "A0016C880162017C3686B18A3D4780";
        assertEquals(31, instance.one(input));
    }

    @Test
    void hexToBits() {
        input = "D2FE28";
        assertEquals("110100101111111000101000", instance.hexToBits(input));
    }

    @Test
    void parseLiteratal() {
        String inp = instance.hexToBits("D2FE28");
        Packet real = instance.parse(inp);
        assertEquals(6, real.version);
        assertEquals(4, real.type);
        assertEquals("2021", real.value);
    }
    @Test
    void parseOperatorLengthtype0() {
        String inp = instance.hexToBits("38006F45291200");
        Packet real = instance.parse(inp);
        assertEquals(1, real.version);
        assertEquals(6, real.type);
        assertEquals(2, real.subs.size());
        assertEquals("10", real.subs.get(0).value);
        assertEquals("20", real.subs.get(1).value);
    }

    @Test
    void parseOperatorLengthtype1() {
        String inp = instance.hexToBits("EE00D40C823060");
        Packet real = instance.parse(inp);
        assertEquals(7, real.version);
        assertEquals(3, real.type);
        assertEquals(3, real.subs.size());
        assertEquals("1", real.subs.get(0).value);
        assertEquals("2", real.subs.get(1).value);
        assertEquals("3", real.subs.get(2).value);
    }

    @Test
    void testOperatorWithinOperator(){
        String inp = instance.hexToBits("8A004A801A8002F478");
        Packet real = instance.parse(inp);
        assertPacket(real, 4, 2, 1);

        Packet sub = real.subs.get(0);

        assertPacket(sub, 1, 2, 1);

        Packet subsub = sub.subs.get(0);
        assertPacket(subsub, 5,2,1);
        Packet lit = subsub.subs.get(0);
        assertPacket(lit, 6,4,0);

        assertEquals(16, instance.sumVersions(real));
        int a = 0;
    }

    @Test
    void testCase1(){
        String inp = instance.hexToBits("8A004A801A8002F478");
        Packet real = instance.parse(inp);
        assertEquals(16, instance.sumVersions(real));
    }
    @Test
    void testCase2(){
        String inp = instance.hexToBits("620080001611562C8802118E34");
        Packet real = instance.parse(inp);
        assertEquals(12, instance.sumVersions(real));
    }
    @Test
    void testCase3(){
        String inp = instance.hexToBits("C0015000016115A2E0802F182340");
        Packet real = instance.parse(inp);
        assertEquals(23, instance.sumVersions(real));
    }
    @Test
    void testCase4(){
        String inp = instance.hexToBits("A0016C880162017C3686B18A3D4780");
        Packet real = instance.parse(inp);
        assertEquals(31, instance.sumVersions(real));
    }


    @Test
    void twoEvalSum() {
        String inp = instance.hexToBits("C200B40A82");
        Packet real = instance.parse(inp);
        assertEquals(3, instance.eval(real));
    }
    @Test
    void twoEvalProd() {
        String inp = instance.hexToBits("04005AC33890");
        Packet real = instance.parse(inp);
        assertEquals(54, instance.eval(real));
    }
    @Test
    void twoEvalmin() {
        String inp = instance.hexToBits("880086C3E88112");
        Packet real = instance.parse(inp);
        assertEquals(7, instance.eval(real));
    }
    @Test
    void twoEvalmax() {
        String inp = instance.hexToBits("CE00C43D881120");
        Packet real = instance.parse(inp);
        assertEquals(9, instance.eval(real));
    }

    @Test
    void twoEvalless() {
        String inp = instance.hexToBits("D8005AC2A8F0");
        Packet real = instance.parse(inp);
        assertEquals(1, instance.eval(real));
    }
    @Test
    void twoEvalgreater() {
        String inp = instance.hexToBits("F600BC2D8F");
        Packet real = instance.parse(inp);
        assertEquals(0, instance.eval(real));
    }
    @Test
    void twoEvalEqual() {
        String inp = instance.hexToBits("9C005AC2F8F0");
        Packet real = instance.parse(inp);
        assertEquals(0, instance.eval(real));
    }

    @Test
    void twoEvalmulti() {
        String inp = instance.hexToBits("9C0141080250320F1802104A08");
        Packet real = instance.parse(inp);
        assertEquals(1, instance.eval(real));
    }
    @Test
    void twoEval() {
        String inp = instance.hexToBits("");
        Packet real = instance.parse(inp);
        assertEquals(9, instance.eval(real));
    }

    @Test
    void getValue() {

        assertEquals("2021", instance.getLiteralValue("110100101111111000101000", new Packet(-1,-1)));
    }

    public static void assertPacket(Packet p, int version, int type, int subsize){
        assertEquals(version, p.version);
        assertEquals(type, p.type);
        assertEquals(subsize, p.subs.size());
    }
}
