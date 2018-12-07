package com.attend
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class EmployeeController {

    static scaffold=Employee
}
