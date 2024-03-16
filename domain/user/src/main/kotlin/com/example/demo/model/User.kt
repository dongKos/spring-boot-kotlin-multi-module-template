package com.example.demo.model

import com.example.demo.persistence.CustomPersistable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
    val name: String,
    val age: Long,
    val loginType: LoginType,
) : CustomPersistable<Long>() {
    @Id @GeneratedValue
    override val id: Long? = null

    val roles: String = "ROLE_USER"

    fun getRoleList(): List<String> {
        return listOf(*roles.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
    }

    enum class LoginType {
        PASSWORD,
        GOOGLE,
    }

    override fun toString(): String {
        return "User(name='$name', age=$age, id=$id)"
    }
}
