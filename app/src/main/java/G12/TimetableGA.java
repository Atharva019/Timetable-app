package G12;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.util.Log;

import com.example.ttgmp.activity_result;
import com.example.ttgmp.assignSubTech;
import com.example.ttgmp.roomActivity;
import com.example.ttgmp.subjects;
import com.example.ttgmp.working_days;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;




/**
 * Don't be daunted by the number of classes in this chapter -- most of them are
 * just simple containers for information, and only have a handful of properties
 * with setters and getters.
 * 
 * The real stuff happens in the GeneticAlgorithm class and the Timetable class.
 * 
 * The Timetable class is what the genetic algorithm is expected to create a
 * valid version of -- meaning, after all is said and done, a chromosome is read
 * into a Timetable class, and the Timetable class creates a nicer, neater
 * representation of the chromosome by turning it into a proper list of Classes
 * with rooms and professors and whatnot.
 * 
 * The Timetable class also understands the problem's Hard Constraints (ie, a
 * professor can't be in two places simultaneously, or a room can't be used by
 * two classes simultaneously), and so is used by the GeneticAlgorithm's
 * calcFitness class as well.
 * 
 * Finally, we overload the Timetable class by entrusting it with the
 * "database information" generated here in initializeTimetable. Normally, that
 * information about what professors are employed and which classrooms the
 * university has would come from a database, but this isn't a book about
 * databases so we hardcode it.
 * 
 *
 */
public class TimetableGA {
	public static int key_count =1;
	public static Workbook wb;
    public static void main(String[] args) throws IOException {
    	// Get a Timetable object with all the available information.
        Timetable timetable = initializeTimetable();
        
        // Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.01, 0.9, 2, 5);
        
        // Initialize population
        Population population = ga.initPopulation(timetable);
        
        // Evaluate population
        ga.evalPopulation(population, timetable);
        
        // Keep track of current generation
        int generation = 1;
        
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, 1000) == false
            && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
			Log.d(TAG, "main: G"+ generation + " Best fitness: " + population.getFittest(0).getFitness());
            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population, timetable);

            // Evaluate population
            ga.evalPopulation(population, timetable);

            // Increment the current generation
            generation++;
        }

        // Print fitness
        timetable.createClasses(population.getFittest(0));
		Log.d(TAG, "main: Solution found in"+generation+" generations");
		Log.d(TAG, "main: Final solution fitness: "+population.getFittest(0).getFitness());
		Log.d(TAG, "main: Clashes: "+ timetable.calcClashes());

        // Print classes
        Class classes[] = timetable.getClasses();
        int classIndex = 1;
		//method 2 to create file

		for (Class bestClass : classes) {
			activity_result obj = new activity_result();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				obj.showResult(classIndex,timetable.getModule(bestClass.getModuleId()).getModuleName(),
						timetable.getGroup(bestClass.getGroupId()).getGroupId(),
						timetable.getRoom(bestClass.getRoomId()).getRoomNumber(),
						timetable.getProfessor(bestClass.getProfessorId()).getProfessorName(),
						timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
			}

//            System.out.println("Class " + classIndex + ":");
//            System.out.println("Module: " +
//                    timetable.getModule(bestClass.getModuleId()).getModuleName());
//            System.out.println("Group: " +
//                    timetable.getGroup(bestClass.getGroupId()).getGroupId());
//            System.out.println("Room: " +
//                    timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
//            System.out.println("Professor: " +
//                    timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
//            System.out.println("Time: " +
//                    timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
//            System.out.println("-----");
            classIndex++;
        }
    }

    /**
     * Creates a Timetable with all the necessary course information.
     * 
     * Normally you'd get this info from a database.
     * 
     * @return
     */
	public static Timetable initializeTimetable() {
		// Create timetable
		Timetable timetable = new Timetable();

		// Set up rooms
//		timetable.addRoom(1, "A Div", 60);
//		timetable.addRoom(2, "B Div", 60);
		String[] room_arr = roomActivity.str_r.split("#");
		for (int i=0;i<room_arr.length;i++) {
			int count = 1;
			timetable.addRoom(count, room_arr[i], 60);
			count++;
		}

		// Set up timeslots
		String[] sel_days = working_days.days.split(",");
		int countTS = 1;
		for (int i=0;i<sel_days.length;i++) {
			timetable.addTimeslot(countTS, sel_days[i]+" 9:00 - 10:00");countTS++;
			timetable.addTimeslot(countTS, sel_days[i]+" 10:00 - 11:00");countTS++;
			timetable.addTimeslot(countTS, sel_days[i]+" 11.15 - 12.15");countTS++;
			timetable.addTimeslot(countTS, sel_days[i]+" 12.15 - 1.15");countTS++;
			timetable.addTimeslot(countTS, sel_days[i]+" 2:00 - 3:00");countTS++;
			timetable.addTimeslot(countTS, sel_days[i]+" 3:00 - 4:00");countTS++;
		}

		// Set up professors
		String [] staff_arr = assignSubTech.staff.split(",");
		int countPfID = 1;
		for (int j=1;j<=4;j++) {
			for (int i = 0; i < staff_arr.length; i++) {
				timetable.addProfessor(countPfID, staff_arr[i]);
				countPfID++;
			}
		}

		// Set up modules and define the professors that teach them
		String[] mod_arr = assignSubTech.SUBS.split(",");
		int[] index = {1,2,3,4,5,6,7};
		int ct = 0;
		int count = 1;
		for (int j=1;j<=4;j++) {
			for (int i = 0; i < mod_arr.length; i++) {

				timetable.addModule(count, subjects.department, mod_arr[i], new int[index[ct]]);
				count++;
				ct++;
				if (ct==6){
					ct=0;
				}
			}
		}
		timetable.addModule(count,subjects.department,mod_arr[0],new int[]{1});
		timetable.addModule(count,subjects.department,mod_arr[1],new int[]{2});

		// Set up student groups and the modules they take.
//		String[] days_arr = working_days.days.split(",");
//		for (int i=0;i< days_arr.length;i++) {
//			int count =1;
		int[] arr = new int[30];
		int c=0;
		for (int i=1;i<=30;i++){
			arr[c] = i;
			c++;
		}
			timetable.addGroup(1, 60, arr);
//			count++;
//		}
		timetable.addGroup(2, 60, arr);
	
		return timetable;
	}
}
