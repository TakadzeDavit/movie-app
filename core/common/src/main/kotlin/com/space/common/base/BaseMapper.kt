package com.space.common.base

interface BaseMapper<in MODEL_A, out MODEL_B> {
    fun map(input: MODEL_A) : MODEL_B
}