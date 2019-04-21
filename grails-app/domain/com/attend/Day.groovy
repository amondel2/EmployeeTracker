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
        emds cascade: "all-delete-orphan"
        version false
        sort myday:'desc'
    }

    static hasMany=[emds:EmployeeDay]

    Date myday
}