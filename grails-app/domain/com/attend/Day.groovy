package com.attend

class Day implements Serializable {

    private static final long serialVersionUID = 1

    static constraints = {
    }

    @Override
    String toString(){
        if(myday){
            return myday
        }
    }

    static mapping = {
        version false
    }

    static hasMany=[emds:EmployeeDay]

    Date myday
}