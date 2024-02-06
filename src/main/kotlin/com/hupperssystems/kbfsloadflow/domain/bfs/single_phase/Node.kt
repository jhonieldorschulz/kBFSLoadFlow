package com.hupperssystems.kbfsloadflow.domain.bfs.single_phase

import org.apache.commons.math3.complex.Complex

class Node{
    var number: Int = 0
    var power: Complex = Complex(0.0 , 0.0)
    var voltage: Complex = Complex(0.0, 0.0)
    var currentFlow: Complex = Complex(0.0 , 0.0)
    var previousVoltage: Double? = null
    var injectedCurrent: Complex = Complex(0.0 , 0.0)
    var convergedNode: Boolean = false



    constructor(number: Int, power: Complex, voltage: Complex){
        this.number = number
        this.power = power
        this.voltage = voltage
        this.injectedCurrent = power.divide(voltage).conjugate()
        this.currentFlow = this.injectedCurrent
    }

    fun updateNode() {
        injectedCurrent = power.divide(voltage).conjugate()
        currentFlow = Complex(injectedCurrent!!.real, injectedCurrent!!.imaginary)
        var checkedConvergence = Math.abs(voltage.abs() - previousVoltage!!)
        convergedNode = (checkedConvergence <= 0.0002)
    }

    override fun toString(): String {
        return "Node(number=$number, power=$power, voltage=$voltage, currentFlow=$currentFlow, previousVoltage=$previousVoltage, injectedCurrent=$injectedCurrent, convergedNode=$convergedNode)"
    }


}

