package com.hupperssystems.kbfsloadflow.domain.bfs.single_phase

import org.apache.commons.math3.complex.Complex

class Layer {
    var layer: Int = 0
    var branches: MutableList<Branch> = mutableListOf()
    var sumCurrent: Complex = Complex(0.0, 0.0)

    constructor(layer: Int, branches: MutableList<Branch>) {
        this.layer = layer
        this.branches = branches
    }

    constructor(layer: Int, branch: Branch) {
        this.layer = layer
        this.branches.add(branch)
    }

    override fun toString(): String {
        return "Layer(layer=$layer, branches=$branches, sumCurrent=$sumCurrent)"
    }


}