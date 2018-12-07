package com.attend
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class DayController {

    static scaffold=Day
}
