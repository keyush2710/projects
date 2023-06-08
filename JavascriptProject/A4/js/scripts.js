/*
 * CSCI 1170: Introduction to Web Design and Development
 * Winter 2021 (Online edition)
 * Author: Keyush Piyushkumar Patel (B00885798)
 *
 * This file contains the main JS file for the timetable page on this website.
 * 
 */
let jedi=JSON.parse(jsonDataUni)/*parsing json data*/
let obj="";
for(i=0;i<dataUni.length;i++)
{
    /* adding paragraph inside timetable-data-container*/
    document.getElementById("timetable-data-container").innerHTML+="<p class='code-name'>"+dataUni[i].courseCode+" - "+dataUni[i].courseName+"</p>";
    var div = document.createElement('div');/*creating an element*/
    div.id = 'container'+i;/* setting id to the element*/
    div.className='timetable';/*setting className to the element*/
    /* appending div in the timetable-data-container*/
    document.getElementById("timetable-data-container").appendChild(div);
    /* adding paragraph inside container+i*/
    document.getElementById("container"+i).innerHTML+="<p class='instructor'>Course Instructor: <span>"+dataUni[i].instructor+"</span></p>";
    document.getElementById("container"+i).innerHTML+="<p class='location'>Location: "+dataUni[i].location+"</p>";
    document.getElementById("container"+i).innerHTML+="<p class='schedule'>Schedule: "+dataUni[i].scheduleDays+"("+dataUni[i].scheduleTime+")</p>";
    document.getElementById("container"+i).innerHTML+="<p class='max'>Enrollment(max): <span>"+dataUni[i].enrollmentMax+"</span></p>"
    document.getElementById("container"+i).innerHTML+="<p class='current'>Enrollment(current): <span>"+dataUni[i].enrollmentCurrent+"</span></p>"
    document.getElementById("container"+i).innerHTML+="<p class='availability'>Enrollment(availability): <span>"+dataUni[i].enrollmentAvailability+"</span></p></div>"
    
}


