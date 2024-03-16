package com.example.demo.model

import com.example.demo.persistence.CustomPersistable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class UserPassword(
    val userId: Long,
    val password: String,
) : CustomPersistable<Long>() {
    @Id @GeneratedValue
    override val id: Long? = null
}
