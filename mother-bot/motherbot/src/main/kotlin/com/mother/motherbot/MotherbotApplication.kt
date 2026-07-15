package com.mother.motherbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MotherbotApplication

fun main(args: Array<String>) {
	runApplication<MotherbotApplication>(*args)
}
