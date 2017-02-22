/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.attend

/**
 *
 * @author aaron
 */
class Report {
    WorkType type
    def size

    Report(WorkType t,num) {
        type = t
        size = num
    }
}

