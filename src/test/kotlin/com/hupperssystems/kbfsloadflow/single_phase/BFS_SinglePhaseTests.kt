package com.hupperssystems.kbfsloadflow.single_phase

import com.hupperssystems.kbfsloadflow.domain.bfs.single_phase.BFS_SinglePhase
import com.hupperssystems.kbfsloadflow.domain.bfs.single_phase.Branch
import com.hupperssystems.kbfsloadflow.domain.bfs.single_phase.Layer
import com.hupperssystems.kbfsloadflow.domain.bfs.single_phase.Node
import org.apache.commons.math3.complex.Complex
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class BFS_SinglePhaseTests {

    @Test
    fun testSistemaAlimentador1() {
        val nodes : MutableList<Node> = mutableListOf()

        /**
         * Barra 2    kw=120.0    kvar=108.0    kv=11    prioridade=1
         * Barra 3    kw=72.00    kvar=48.00    kv=11    prioridade=0,5hjuuuuuuuuuuuuuuuuyyyyyyyyyyyyyyyyyyyyyy
         * Barra 4    kw=180.0    kvar=156.0    kv=11    prioridade=0,125
         * Barra 5    kw=90.00    kvar=60.00    kv=11    prioridade=0,125
         * Barra 6    kw=21.60    kvar=13.00    kv=11    prioridade=0,125
         * Barra 7    kw=21.60    kvar=17.00    kv=11    prioridade=0,125
         * Barra 8    kw=15.60    kvar=12.00    kv=11    prioridade=0,125
         * Barra 9    kw=19.00    kvar=13.00    kv=11    prioridade=0,125
         * Barra 10   kw=24.00    kvar=12.00    kv=11    prioridade=1
         * Barra 11   kw=19.20    kvar=11.00    kv=11    prioridade=0,25
         * Barra 12   kw=60.00    kvar=48.00    kv=11    prioridade=0,5
         * Barra 13   kw=126.0    kvar=108.0    kv=11    prioridade=0,5
         * Barra 14   kw=30.00    kvar=18.00    kv=11    prioridade=1
         * Barra 15   kw=48.00    kvar=30.00    kv=11    prioridade=0,25
         * Barra 68   kw=120.0    kvar=72.00    kv=11    prioridade=1
         * Barra 69   kw=48.00    kvar=36.00    kv=11    prioridade=1
         */
        //0
        nodes.add(Node(1, Complex(0.0, 0.0), Complex(11000.0, 0.0)))
        //1
        nodes.add(Node(2, Complex(120000.00, 108000.00), Complex(11000.0, 0.0)))
        //2
        nodes.add(Node(3, Complex(72000.0, 48000.0), Complex(11000.00, 0.0)))
        //3
        nodes.add(Node(4, Complex(180000.00, 156000.0), Complex(11000.00, 0.0)))
        //4
        nodes.add(Node(5, Complex(90000.00, 60000.00), Complex(11000.00, 0.0)))
        //5
        nodes.add(Node(6, Complex(21600.00, 13000.00), Complex(11000.00, 0.0)))
        //6
        nodes.add(Node(7, Complex(21600.00, 17000.00), Complex(11000.00, 0.0)))
        //7
        nodes.add(Node(8, Complex(15600.00, 12000.00), Complex(11000.00, 0.0)))
        //8
        nodes.add(Node(9, Complex(19000.00, 13000.00), Complex(11000.00, 0.0)))
        //9
        nodes.add(Node(10, Complex(24000.00, 12000.00), Complex(11000.00, 0.0)))
        //10
        nodes.add(Node(11, Complex(19200.00, 11000.00), Complex(11000.00, 0.0)))
        //11
        nodes.add(Node(12, Complex(60000.00, 48000.00), Complex(11000.00, 0.0)))
        //12
        nodes.add(Node(13, Complex(126000.00, 108000.00), Complex(11000.00, 0.0)))
        //13
        nodes.add(Node(14, Complex(30000.00, 18000.00), Complex(11000.00, 0.0)))
        //14
        nodes.add(Node(15, Complex(48000.00, 30000.00), Complex(11000.00, 0.0)))
        //15
        nodes.add(Node(68, Complex(120000.00, 72000.00), Complex(11000.00, 0.0)))
        //16
        nodes.add(Node(69, Complex(48000.00, 36000.00), Complex(11000.00, 0.0)))

        //        nodes.add(new Node(6, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));

        val branches = ArrayList<Branch>()

        val layers : MutableList<Layer> = mutableListOf()
        /**
         * LAYER 1
         */
        branches.add(Branch(nodes[0], nodes[1], Complex(1.097, 1.074)))


        /**
         * LAYER 2
         */
        branches.add(Branch(nodes[1], nodes[2], Complex(1.463, 1.432)))


        /**
         * LAYER 3
         */
        branches.add(Branch(nodes[2], nodes[3], Complex(0.731, 0.716)))

        /**
         * LAYER 4
         */
        branches.add(Branch(nodes[3], nodes[4], Complex(0.366, 0.358)))
        branches.add(Branch(nodes[3], nodes[9], Complex(1.080, 0.734)))


        /**
         * LAYER 5
         */
        branches.add(Branch(nodes[4], nodes[5], Complex(1.828, 1.790)))
        branches.add(Branch(nodes[9], nodes[10], Complex(1.620, 1.101)))


        /**
         * LAYER 6
         */
        branches.add(Branch(nodes[5], nodes[6], Complex(1.097, 1.074)))
        branches.add(Branch(nodes[10], nodes[11], Complex(1.080, 0.734)))

        /**
         * LAYER 7
         */
        branches.add(Branch(nodes[6], nodes[7], Complex(0.731, 0.716)))
        branches.add(Branch(nodes[6], nodes[15], Complex(1.080, 0.734)))
        branches.add(Branch(nodes[11], nodes[12], Complex(1.350, 0.917)))

        /**
         * LAYER 8
         */
        branches.add(Branch(nodes[7], nodes[8], Complex(0.731, 0.716)))
        branches.add(Branch(nodes[15], nodes[16], Complex(1.620, 1.101)))
        branches.add(Branch(nodes[12], nodes[13], Complex(0.810, 0.550)))

        /**
         * LAYER 9
         */
        branches.add(Branch(nodes[13], nodes[14], Complex(1.944, 1.321)))


        //        layers.add(new Layer(1, branches.subList(0, 1)));
        //        layers.add(new Layer(2, branches.subList(1, 2)));
        //        layers.add(new Layer(3, branches.subList(2, 3)));
        //        layers.add(new Layer(4, branches.subList(3, 5)));
        //        layers.add(new Layer(5, branches.subList(5, 7)));
        //        layers.add(new Layer(6, branches.subList(7, 9)));
        //        layers.add(new Layer(7, branches.subList(9, 12)));
        //        layers.add(new Layer(8, branches.subList(12, 15)));
        //        layers.add(new Layer(9, branches.subList(15, 16)));
        //
        //        System.out.println("-----------------------------------------");
        //        System.out.println("-----------------------------------------");
        //        layers.forEach(l -> {
        //            System.out.println("Layer " + l.getLayer());
        //            l.getBranches().forEach(b -> {
        //                System.out.println(b.getOrigin().getNumber() + " ---> " + b.getDestiny().getNumber());
        //            });
        //        });

        val bfs = BFS_SinglePhase(branches, nodes)

        bfs.maxIterarions = 50

        //
        bfs.iteratePowerFlow()

    }


    @Test
    fun testSistema4Nodes() {

        val nodes : MutableList<Node> = mutableListOf()

        /**
         * Cargas
         * S2 = (1.28 + j1.28) pu = (12.8 + j12.8 )MVA
         * S3 = (0.32 + j0.16) pu = (3.2  + j1.60 )MVA
         * S4 = (1.6  + j0.80) pu = (16   + j8.00 )MVA
         */

        //S1
        nodes.add(Node(1, Complex(0.0, 0.0), Complex(1.05, 0.0)))
        //S2
        nodes.add(Node(2, Complex(1.28, 1.28), Complex(1.05, 0.0)))
        //S3
        nodes.add(Node(3, Complex(0.32, 0.16), Complex(1.05, 0.0)))
        //S4
        nodes.add(Node(4, Complex(1.6, 0.8), Complex(1.05, 0.0)))


        val branches = ArrayList<Branch>()

        val layers: MutableList<Layer> = mutableListOf()

        /**
         * LAYER 1
         */
        branches.add(Branch(nodes[0], nodes[1], Complex(0.0236, 0.0233)))
        /**
         * LAYER 2
         */
        branches.add(Branch(nodes[1], nodes[2], Complex(0.0003, 0.0002)))
        /**
         * LAYER 3
         */
        branches.add(Branch(nodes[2], nodes[3], Complex(0.0051, 0.0005)))


        //        layers.add(new Layer(1, branches.subList(0, 1)));
        //        layers.add(new Layer(2, branches.subList(1, 2)));
        //        layers.add(new Layer(3, branches.subList(2, 3)));
        //
        //        BFS bfs = new BFS(nodes, branches, layers);

        val bfs = BFS_SinglePhase(branches, nodes)

        bfs.maxIterarions = 50
//        bfs.setPrintIterations(false)

        bfs.iteratePowerFlow()

    }
}