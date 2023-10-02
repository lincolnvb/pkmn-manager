package com.looqbox.pkmnmanager.src.utils

import org.springframework.stereotype.Service

@Service
class SortUtils {

    fun mergeSort(arr: List<String>): List<String> {
        // Caso base: Se a lista tiver tamanho 1 ou menos, ela já está ordenada
        if (arr.size <= 1) {
            return arr
        }

        // Calcula o índice do meio da lista
        val middle = arr.size / 2

        // Divide a lista em duas metades: esquerda e direita
        val left = arr.subList(0, middle)
        val right = arr.subList(middle, arr.size)

        // Recursivamente ordena ambas as metades e combina elas de volta
        return merge(mergeSort(left), mergeSort(right))
    }

    // Define a função merge que combina duas listas ordenadas em uma única lista ordenada
    private fun merge(left: List<String>, right: List<String>): List<String> {

        // Inicializa uma lista vazia para guardar o resultado
        val result = mutableListOf<String>()

        // Inicializa os índices para as listas esquerda e direita
        var leftIndex = 0
        var rightIndex = 0

        // Enquanto houver elementos em ambas as listas, compara e insere o menor elemento no resultado
        while (leftIndex < left.size && rightIndex < right.size) {
            if (left[leftIndex] <= right[rightIndex]) {
                result.add(left[leftIndex])
                leftIndex++
            } else {
                result.add(right[rightIndex])
                rightIndex++
            }
        }

        // Se ainda houver elementos na lista esquerda, adiciona todos no resultado
        while (leftIndex < left.size) {
            result.add(left[leftIndex])
            leftIndex++
        }

        // Se ainda houver elementos na lista direita, adiciona todos no resultado
        while (rightIndex < right.size) {
            result.add(right[rightIndex])
            rightIndex++
        }

        return result
    }

    fun sortByLength(arr: List<String>): List<String> {
        // Encontrar o comprimento máximo da string
        var maxLen = 0
        for (str in arr) {
            if (str.length > maxLen) {
                maxLen = str.length
            }
        }

        // Criar "buckets" baseados no comprimento da string
        val buckets = Array<MutableList<String>>(maxLen + 1) { mutableListOf() }
        for (str in arr) {
            buckets[str.length].add(str)
        }

        // Converter "buckets" para uma lista unificada
        val sortedList = mutableListOf<String>()
        for (bucket in buckets) {
            for (str in bucket) {
                sortedList.add(str)
            }
        }

        return sortedList
    }
}