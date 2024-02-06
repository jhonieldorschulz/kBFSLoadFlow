package com.hupperssystems.kbfsloadflow.domain.bfs.single_phase

import java.util.stream.Collectors
import java.util.ArrayList
import org.apache.commons.math3.complex.Complex



class BFS_SinglePhase {
    var branches: List<Branch> = ArrayList()
    var layers : MutableList<Layer> = mutableListOf()
    private var nodes: List<Node> = ArrayList()

    var converge: Boolean = false
    var printIterations: Boolean = true
    var iterations: Int = 0


    var maxIterarions: Int? = null

    constructor(branches: List<Branch>, nodes: List<Node>){
        this.branches = branches
        this.nodes = nodes
        generateLayers()
        printLayers()
    }

    constructor(nodes: List<Node>, branches: List<Branch>, layers: MutableList<Layer>){
        this.nodes = nodes
        this.branches = branches
        this.layers = layers


    }

    fun backward() {
        if (printIterations!!) {
            println("BACKWARD")
            println("-----------------------------------------------------------------")
        }

        for (i in layers.indices.reversed()) {
            layers[i].branches.forEach { branch ->
                branch.origin.currentFlow = branch.origin.currentFlow.add(branch.destiny.currentFlow)
                branch.current = Complex(branch.destiny.currentFlow.real,
                        branch.destiny.currentFlow.imaginary)
                branch.IZ = branch.current.multiply(branch.impedance)

                if (printIterations!!) {
                    println("-------------------------------")
                    println("Branch: " + branch.origin.number + " ---> " + branch.destiny.number)

                    println("Current:")
                    println("-------------------------------------------")
                    println("current.abs():" + branch.current.abs())
                    println("current.getReal():" + branch.current.real)
                    println("current.getImag():" + branch.current.imaginary)
                    println("current.AngRad():" + branch.current.log().imaginary)
                    println("current.AngDeg():" + Math.toDegrees(branch.current.log().imaginary))

                    println("IZ:")
                    println("-------------------------------------------")
                    println("iz.abs():" + branch.IZ.abs())
                    println("iz.getReal():" + branch.IZ.real)
                    println("iz.getImag():" + branch.IZ.imaginary)
                    println("iz.AngRad():" + branch.IZ.log().imaginary)
                    println("iz.AngDeg():" + Math.toDegrees(branch.IZ.log().imaginary))
                }

            }
        }
    }

    fun forward() {
        if (printIterations) {
            println("FORWARD")
            println("-----------------------------------------------------------------")
        }

        layers.forEach { layer ->
            layer.branches.forEach { (origin, destiny, impedance, current, IZ) ->
                if (printIterations!!) {
                    println(origin.number.toString() + " ---> " + destiny.number)
                    println("branch.getOrigin().getVoltage():" + origin.voltage)
                    println("branch.getIZ():" + IZ)
                    println("branch.getCurrent():" + current)

                    println("-------------------------------------------------")
                    println("abs: " + current.abs() + " ang.: " + Math.toDegrees(current.log().imaginary))
                    println("-------------------------------------------------")
                    println("branch.getImpedance()" + impedance)
                }

                destiny.previousVoltage = destiny.voltage.abs()
                destiny.voltage = origin.voltage.subtract(IZ)
                destiny.updateNode()
                //                branch.getDestiny().setInjectedCurrent(branch.getDestiny().getPower().divide(branch.getDestiny().getVoltage()).conjugate());
                //
                //                branch.getDestiny().setCurrentFlow(new Complex(branch.getDestiny().getInjectedCurrent().getReal(), branch.getDestiny().getInjectedCurrent().getImaginary()));
                //                branch.getDestiny().setPower( branch.getDestiny().getVoltage().multiply( branch.getDestiny().getInjectedCurrent()).conjugate());

                //                System.out.println("branch.getDestiny().getVoltage():" + branch.getDestiny().getVoltage());
                //                System.out.println("branch.getDestiny().getPower():" + branch.getDestiny().getPower());
                //                System.out.println("branch.getDestiny().getInjectedCurrent():" + branch.getDestiny().getInjectedCurrent());
            }
        }
        if (printIterations!!) {
            println("-----------------------------------------------------------------")
        }

    }

    fun iteratePowerFlow() {
        //
        while ((!this.converge)) {
            if ((!converge)) {
                if (printIterations!!) {
                    println("\n########################################################################")
                    println("\nIteração: " + (iterations!! + 1))
                    println("-------------------------------------")
                }

                backward()
                forward()
                checkIteration()

                iterations++
                if (iterations == maxIterarions) {
                    break
                }
            } else {
                break
            }
            if (printIterations!!) {
                println("-------------------------------------")
            }

        }

        println("----------------------------------------------------")
        println("Converge:" + converge!!)
        println("Iterations: " + iterations!!)

    }


    private fun generateLayers() {
        for (branch in branches) {
            println("Processando Branch: " + branch.origin.number + " ---> " + branch.destiny.number)
            if (isNewLayer(branch)!!) {
                layers.add(Layer(layers.size + 1, branch))
            } else {
                for (i in layers.indices) {
                    if (i > 0) {
                        for ((_, destiny) in layers[i - 1].branches) {
                            if (branch.origin.number.equals(destiny.number)) {
                                layers[i].branches.add(branch)
                            }
                        }

                    } else {
                        for ((origin) in layers[i].branches) {
                            if (branch.origin.number.equals(origin.number)) {
                                layers[i].branches.add(branch)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isNewLayer(branch: Branch): Boolean? {
        var isNewLayer: Boolean? = true
        for (i in layers.indices) {
            if (i > 0) {
                isNewLayer = !layers[i - 1].branches.stream().filter { (_, destiny) -> branch.origin.number.equals(destiny.number) }
                        .findAny()
                        .isPresent
            } else {
                isNewLayer = !layers[i].branches.stream().filter { (origin) -> branch.origin.number.equals(origin.number) }
                        .findAny()
                        .isPresent
            }
        }

        return isNewLayer
    }

    private fun printLayers() {
        for (layer in layers) {
            println("\nLayer: " + layer.layer)
            println("-----------------------------------------")
            for ((origin, destiny) in layer.branches) {
                println("Ori.:[" + origin.number + "] ---> Dest.:[" + destiny.number + "]")
            }
        }
    }


    fun checkIteration() {

        nodes.forEach { node ->
            if (printIterations!!) {
                println("Node: " + node.number)
                println("----------------------------------")
                println("Voltage:" + node.voltage)
                println("---------------------------------------------")
                println("abs.: " + node.voltage.abs() + " Ang. Deg.:" + Math.toDegrees(node.voltage.log().imaginary))
                println("abs.: " + node.voltage.abs() + " log.:" + node.voltage.log())
                println("abs.: " + node.voltage.abs() + " acos.:" + node.voltage.acos())
                println("abs.: " + node.voltage.abs() + " atan.:" + node.voltage.atan())
                println("abs.: " + node.voltage.abs() + " Math.atan.:" + Math.atan(node.voltage.real / node.voltage.real))
                println("---------------------------------------------")
                println("Current Flow:" + node.currentFlow)
            }
            node.injectedCurrent = node.power.divide(node.voltage).conjugate()
            node.currentFlow = Complex(node.injectedCurrent.real, node.injectedCurrent.imaginary)
            if (printIterations!!) {
                println("Injected Current:" + node.injectedCurrent)
            }


        }

        var convergedNodes: List<Node> = nodes.filter { node -> node.convergedNode == true }


        if (printIterations!!) {
            println("Converged Nodes:" + convergedNodes.size)
        }


        this.converge = convergedNodes.size == nodes.size - 1
    }



}