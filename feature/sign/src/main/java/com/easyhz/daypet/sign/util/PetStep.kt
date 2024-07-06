package com.easyhz.daypet.sign.util

enum class PetStep {
    PROFILE, INFO, ATTRIBUTE, MEMO;
    companion object {
        val firstProgress = (1f / (PetStep.entries.size * 2))
        val stepProgress = (1f / PetStep.entries.size)
    }

    fun nextStep(): PetStep? {
        return entries.getOrNull(this.ordinal + 1)
    }
}