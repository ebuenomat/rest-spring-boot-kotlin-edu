package br.com.ebueno.services

import br.com.ebueno.model.Person
import org.springframework.stereotype.Service
import org.yaml.snakeyaml.internal.Logger
import java.util.concurrent.atomic.AtomicLong

@Service
class PersonServices {

    private val counter: AtomicLong = AtomicLong()
    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<Person> {
        logger.warn("Finding all people!")

        val persons: MutableList<Person> = ArrayList()
        for (i in 0..7) {
            val person = mockPerson(i)
            persons.add(person)
        }
        return persons
    }

    fun findById(id: Long): Person {
        logger.warn("Finding one person")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Eduardo"
        person.lastName = "Bueno"
        person.adress = "Rua A"
        person.gender = "Masculino"

        return person
    }

    fun create(person: Person) = person

    fun update(person: Person) = person

    fun delete(id: Long) {}

    private fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $i"
        person.lastName = "Last Name $i"
        person.adress = "Some Address in Brasil"
        person.gender = "Male"
        return person
    }
}