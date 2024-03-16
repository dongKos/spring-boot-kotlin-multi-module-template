package com.example.demo.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class CustomPersistable<PK : Serializable?> : Identifiable<PK> {
    override fun equals(other: Any?): Boolean {
        if (null == other) {
            return false
        }
        if (this === other) {
            return true
        }
        if (javaClass != ProxyUtils.getUserClass(other)) {
            return false
        }
        val that = other as CustomPersistable<*>
        return if (null == id) {
            false
        } else {
            id == that.id
        }
    }

    override fun hashCode(): Int {
        var hashCode = 17
        hashCode += if (null == id) 0 else id.hashCode() * 31
        return hashCode
    }

    override fun toString(): String {
        return String.format("Entity of type %s with id : %s", javaClass.name, id)
    }

    @CreatedDate
    @Column(updatable = false)
    var createdAt: Instant = Instant.now()

    @LastModifiedDate
    var updatedAt: Instant? = null
}
