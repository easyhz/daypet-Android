package com.easyhz.daypet.common.extension


fun <K, V> Map<K, V>.toHashMap(): HashMap<K, V> {
    return HashMap(this)
}