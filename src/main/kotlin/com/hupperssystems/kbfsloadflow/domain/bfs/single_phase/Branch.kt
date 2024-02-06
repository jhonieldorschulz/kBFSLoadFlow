package com.hupperssystems.kbfsloadflow.domain.bfs.single_phase

import org.apache.commons.math3.complex.Complex

data class Branch(
        val origin: Node,
        val destiny: Node,
        val impedance: Complex,
        var current: Complex = Complex(0.0 , 0.0),
        var IZ: Complex = Complex(0.0 , 0.0)
) {


}