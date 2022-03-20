package com.yang.menu.menuapi.exceptions

open class MenuException : Exception()
class NotExistsUserException(override val message: String = "not exists user.") : MenuException()
class InvalidPasswordException(override val message: String = "invalid password.") : MenuException()