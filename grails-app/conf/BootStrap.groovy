
import com.attend.Day
import com.attend.Employee
import com.attend.EmployeeDay

class BootStrap {

    def init = { servletContext ->
        def employeeList = []
        def e
        try{
            e = new Employee(firstName:"Aditya",lastName:"Kallem")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Aditya","Kallem"))
        }

        try{
            e = new Employee(firstName:"Ravi",lastName:"Yellapu")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Ravi","Yellapu"))
        }

        try{
            e = new Employee(firstName:"Indu",lastName:"Sandadi")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Indu","Sandadi"))
        }

        try{
            e = new Employee(firstName:"Keith",lastName:"Rasweiler")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Keith","Rasweiler"))
        }

        try{
            e = new Employee(firstName:"Amanda",lastName:"Case")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Amanda","Case"))
        }

        try{
            e = new Employee(firstName:"Jay",lastName:"Gazula")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Jay","Gazula"))
        }

        try{
            e = new Employee(firstName:"Amar",lastName:"Shah")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Amar","Shah"))
        }

        try{
            e = new Employee(firstName:"Noah",lastName:"Ginsburg")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Noah","Ginsburg"))
        }

        try{
            e = new Employee(firstName:"Sanath",lastName:"Pothula")
            e.save()
            employeeList.add(e)
        } catch(Exception ex) {
            employeeList.add(Employee.findByFirstNameAndLastName("Sanath","Pothula"))
        }

        def date = new GregorianCalendar(2016,11,31);
        for(def i=0;i<365;i++) {
            date.add(Calendar.DAY_OF_MONTH,1)
            def  day = date.get(Calendar.DAY_OF_WEEK)
            if(day == Calendar.SUNDAY || day ==  Calendar.SATURDAY  ) {
                continue
            }
            def d
            d = Day.findByMyday(date.getTime())
            if(!d) {
                d = new Day()
                d.myday = date.getTime()
                d.save(flush:true)
            }
            //            employeeList.each{ emp ->
            //                def empr = new EmployeeDay()
            //                empr.day = d
            //                empr.emp = emp
            //                try{
            //                    empr.save(flush:true,failOnError:true)
            //                } catch(Exception ex) {
            //                    println ex.getMessage()
            //                }
            //            }
        }
    }
    def destroy = {
    }
}
