package com.example.demo.model

import com.example.demo.persistence.CustomPersistable
import com.example.demo.persistence.Identifiable
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
    val name: String,
    val age: Long,
): CustomPersistable<Long>() {
    @Id @GeneratedValue
    override val id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (name != other.name) return false
        if (age != other.age) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "User(name='$name', age=$age, id=$id)"
    }
}
