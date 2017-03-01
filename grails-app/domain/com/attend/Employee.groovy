package com.attend

class Employee implements Serializable {

    private static final long serialVersionUID = 1

    static constraints = {
        firstName unique:'lastName'
    }

    static mapping = {
        emds cascade: "all-delete-orphan"
        version false
    }

    @Override
    String toString(){
        firstName + " " + lastName
    }


    static hasMany=[emds:EmployeeDay]

    String firstName
    String lastName

}
