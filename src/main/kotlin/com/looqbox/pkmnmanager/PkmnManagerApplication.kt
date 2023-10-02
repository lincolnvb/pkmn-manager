package com.looqbox.pkmnmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PkmnManagerApplication

fun main(args: Array<String>) {
	runApplication<PkmnManagerApplication>(*args)
}
