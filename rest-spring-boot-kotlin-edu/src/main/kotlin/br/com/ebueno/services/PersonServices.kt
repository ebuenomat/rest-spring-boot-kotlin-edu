package br.com.ebueno.services

import br.com.ebueno.exceptions.ResourceNotFoundException
import br.com.ebueno.model.Person
import br.com.ebueno.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.yaml.snakeyaml.internal.Logger
import java.util.concurrent.atomic.AtomicLong

@Service
class PersonServices {

    @Autowired
    private lateinit var repository: PersonRepository
    private val logger = Logger.getLogger(PersonServices::class.java.name)

    fun findAll(): List<Person> {
        logger.warn("Finding all people!")

        return repository.findAll()
    }

    fun findById(id: Long): Person {
        logger.warn("Finding one person")

        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Record not found!") }
    }

    fun create(person: Person) : Person {
        logger.warn("Creating one person with name ${person.firstName}")
        return repository.save(person)
    }

    fun update(person: Person) : Person {
        logger.warn("Updating one person with id ${person.id}")
        val person = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("Record not found!") }
        person.firstName = person.firstName
        person.lastName = person.lastName
        person.adress = person.adress
        person.gender = person.gender

        return repository.save(person)
    }

    fun delete(id: Long) {
        logger.warn("Deleting one person with id: $id!")
        val person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Record not found!") }
        repository.delete(person)
    }
}