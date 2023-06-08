/*
 * CSCI 1170: Introduction to Web Design and Development
 * Winter 2021 (Online edition)
 * Author: Dr. Raghav V. Sampangi (raghav@cs.dal.ca)
 *
 * This file contains JSON Data stored in a JavaScript variable.
 * Include this script if you wish to use this data in Assignment 4.
 * 
 * Description of the data:
 * This data gives simple timetable in a university.
 * Each student has a course code, course name, schedule (day/time), location, enrollment max, enrollment current, enrollment available, and instructor teaching the course.
 * There are 27 courses in total.
 * 
 * Data sources:
 * [1] Course names generated using: https://fishbiscuit.github.io/degree-name-generator/
 * [2] Instructor names generated using: https://www.name-generator.org.uk/?i=2a5twg9t
 * 
 */

var dataUni = [
	{
	  "courseCode": "CSCI0001",
	  "courseName": "DevSecAIOps and Management (1)",
	  "scheduleDays": "T Th",
	  "scheduleTime": "10:00-11:30",
	  "location": "Goldberg 127",
	  "enrollmentMax": 72,
	  "enrollmentCurrent": 45,
	  "enrollmentAvailability": 27,
	  "instructor": "Linda Myers"
	},
	{
	  "courseCode": "CSCI0002",
	  "courseName": "Precision Design and Management",
	  "scheduleDays": "M W F",
	  "scheduleTime": "11:30-12:30",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 99,
	  "enrollmentCurrent": 75,
	  "enrollmentAvailability": 24,
	  "instructor": "Mary Rivera"
	},
	{
	  "courseCode": "CSCI0003",
	  "courseName": "Gamified Architecture",
	  "scheduleDays": "T Th",
	  "scheduleTime": "15:00-16:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 14,
	  "enrollmentCurrent": 10,
	  "enrollmentAvailability": 4,
	  "instructor": "Carol Leonard"
	},
	{
	  "courseCode": "CSCI0004",
	  "courseName": "Interconnected Virtual Reality Design and Management",
	  "scheduleDays": "T Th",
	  "scheduleTime": "13:00-14:30",
	  "location": "Goldberg 143",
	  "enrollmentMax": 59,
	  "enrollmentCurrent": 43,
	  "enrollmentAvailability": 16,
	  "instructor": "Barbara Mcdonald"
	},
	{
	  "courseCode": "CSCI0005",
	  "courseName": "Computational 5G Networks Research and Sustainability",
	  "scheduleDays": "M W",
	  "scheduleTime": "14:30-16:00",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 20,
	  "enrollmentCurrent": 18,
	  "enrollmentAvailability": 2,
	  "instructor": "Amanda Johnson"
	},
	{
	  "courseCode": "CSCI0006",
	  "courseName": "Environmental Sustainability",
	  "scheduleDays": "T Th",
	  "scheduleTime": "08:30-10:00",
	  "location": "Goldberg 143",
	  "enrollmentMax": 33,
	  "enrollmentCurrent": 32,
	  "enrollmentAvailability": 1,
	  "instructor": "Stephanie Davis"
	},
	{
	  "courseCode": "CSCI0007",
	  "courseName": "The Fourth Wave of Industrialization",
	  "scheduleDays": "M W F",
	  "scheduleTime": "10:30-11:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 65,
	  "enrollmentCurrent": 31,
	  "enrollmentAvailability": 34,
	  "instructor": "Jessica Miller"
	},
	{
	  "courseCode": "CSCI0008",
	  "courseName": "Computational 5G Networks Research and Sustainability",
	  "scheduleDays": "M W F",
	  "scheduleTime": "11:30-12:30",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 27,
	  "enrollmentCurrent": 26,
	  "enrollmentAvailability": 1,
	  "instructor": "Sarah Smith"
	},
	{
	  "courseCode": "CSCI0009",
	  "courseName": "Environmental Sustainability",
	  "scheduleDays": "M W F",
	  "scheduleTime": "12:30-13:30",
	  "location": "Goldberg 143",
	  "enrollmentMax": 61,
	  "enrollmentCurrent": 44,
	  "enrollmentAvailability": 17,
	  "instructor": "Elizabeth Wilson"
	},
	{
	  "courseCode": "CSCI0010",
	  "courseName": "Environmental Sustainability & Tech",
	  "scheduleDays": "T Th",
	  "scheduleTime": "13:30-15:00",
	  "location": "Goldberg 127",
	  "enrollmentMax": 14,
	  "enrollmentCurrent": 12,
	  "enrollmentAvailability": 2,
	  "instructor": "Ashley A Taylor"
	},
	{
	  "courseCode": "CSCI0011",
	  "courseName": "Environmental Sustainability & Digital Transformation",
	  "scheduleDays": "T Th",
	  "scheduleTime": "13:30-15:00",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 120,
	  "enrollmentCurrent": 34,
	  "enrollmentAvailability": 86,
	  "instructor": "Ashley M Taylor"
	},
	{
	  "courseCode": "CSCI0012",
	  "courseName": "Cyber-physical Security Design",
	  "scheduleDays": "M Th",
	  "scheduleTime": "09:00-10:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 33,
	  "enrollmentCurrent": 16,
	  "enrollmentAvailability": 17,
	  "instructor": "Ashley S Taylor"
	},
	{
	  "courseCode": "CSCI0013",
	  "courseName": "Cyber-physical Systems",
	  "scheduleDays": "M W F",
	  "scheduleTime": "09:30-10:30",
	  "location": "Goldberg 127",
	  "enrollmentMax": 110,
	  "enrollmentCurrent": 57,
	  "enrollmentAvailability": 53,
	  "instructor": "Jessica Blaese"
	},
	{
	  "courseCode": "CSCI0014",
	  "courseName": "Immersive Experiences for Collaboration",
	  "scheduleDays": "M W F",
	  "scheduleTime": "09:30-10:30",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 57,
	  "enrollmentCurrent": 52,
	  "enrollmentAvailability": 5,
	  "instructor": "Heather James"
	},
	{
	  "courseCode": "CSCI0015",
	  "courseName": "Entrepreneurship and Design",
	  "scheduleDays": "T Th",
	  "scheduleTime": "10:30-12:00",
	  "location": "Goldberg 143",
	  "enrollmentMax": 54,
	  "enrollmentCurrent": 40,
	  "enrollmentAvailability": 14,
	  "instructor": "Heather Jones"
	},
	{
	  "courseCode": "CSCI0016",
	  "courseName": "Analytics for Design",
	  "scheduleDays": "T Th",
	  "scheduleTime": "10:30-12:00",
	  "location": "Goldberg 127",
	  "enrollmentMax": 98,
	  "enrollmentCurrent": 80,
	  "enrollmentAvailability": 18,
	  "instructor": "Xiang Miller"
	},
	{
	  "courseCode": "CSCI0017",
	  "courseName": "Precision Virtual Reality and The Future",
	  "scheduleDays": "T Th",
	  "scheduleTime": "10:30-12:00",
	  "location": "Goldberg 134",
	  "enrollmentMax": 27,
	  "enrollmentCurrent": 13,
	  "enrollmentAvailability": 14,
	  "instructor": "Bo Chang"
	},
	{
	  "courseCode": "CSCI0018",
	  "courseName": "Data Science and Fake News",
	  "scheduleDays": "M W",
	  "scheduleTime": "10:30-12:00",
	  "location": "Goldberg 127",
	  "enrollmentMax": 109,
	  "enrollmentCurrent": 47,
	  "enrollmentAvailability": 62,
	  "instructor": "Juan Ling"
	},
	{
	  "courseCode": "CSCI0019",
	  "courseName": "Internet of Things",
	  "scheduleDays": "M W F",
	  "scheduleTime": "12:30-13:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 54,
	  "enrollmentCurrent": 24,
	  "enrollmentAvailability": 30,
	  "instructor": "Bo Chang"
	},
	{
	  "courseCode": "CSCI0020",
	  "courseName": "Data Science for Management",
	  "scheduleDays": "M W",
	  "scheduleTime": "13:30-15:00",
	  "location": "Goldberg 143",
	  "enrollmentMax": 53,
	  "enrollmentCurrent": 20,
	  "enrollmentAvailability": 33,
	  "instructor": "Tara K Sharma"
	},
	{
	  "courseCode": "CSCI0021",
	  "courseName": "Innovative Smart Spaces and Sustainability (1)",
	  "scheduleDays": "M W F",
	  "scheduleTime": "12:30-13:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 64,
	  "enrollmentCurrent": 59,
	  "enrollmentAvailability": 5,
	  "instructor": "Tara Mohan Sharma"
	},
	{
	  "courseCode": "CSCI0022",
	  "courseName": "Innovative Smart Spaces and Sustainability (2)",
	  "scheduleDays": "M W",
	  "scheduleTime": "15:00-16:30",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 128,
	  "enrollmentCurrent": 60,
	  "enrollmentAvailability": 68,
	  "instructor": "Tara Sharma"
	},
	{
	  "courseCode": "CSCI0023",
	  "courseName": "Innovative Smart Spaces and Sustainability (3)",
	  "scheduleDays": "M W",
	  "scheduleTime": "15:00-16:30",
	  "location": "Goldberg 134",
	  "enrollmentMax": 52,
	  "enrollmentCurrent": 40,
	  "enrollmentAvailability": 12,
	  "instructor": "Tarun Kumar"
	},
	{
	  "courseCode": "CSCI0024",
	  "courseName": "Innovative Smart Spaces and Sustainability (4)",
	  "scheduleDays": "M",
	  "scheduleTime": "09:30-12:30",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 68,
	  "enrollmentCurrent": 47,
	  "enrollmentAvailability": 21,
	  "instructor": "Tarun Rajkumar"
	},
	{
	  "courseCode": "CSCI0025",
	  "courseName": "Analytics for Design (1)",
	  "scheduleDays": "M W F",
	  "scheduleTime": "11:30-12:30",
	  "location": "Goldberg 143",
	  "enrollmentMax": 63,
	  "enrollmentCurrent": 54,
	  "enrollmentAvailability": 9,
	  "instructor": "Ravi Patel"
	},
	{
	  "courseCode": "CSCI0026",
	  "courseName": "Analytics for Design (2)",
	  "scheduleDays": "T Th",
	  "scheduleTime": "11:30-13:00",
	  "location": "Goldberg 134",
	  "enrollmentMax": 32,
	  "enrollmentCurrent": 18,
	  "enrollmentAvailability": 14,
	  "instructor": "Ranveer Singh"
	},
	{
	  "courseCode": "CSCI0027",
	  "courseName": "Analytics for Design (3)",
	  "scheduleDays": "T Th",
	  "scheduleTime": "11:30-13:00",
	  "location": "Online - Synchronous",
	  "enrollmentMax": 69,
	  "enrollmentCurrent": 64,
	  "enrollmentAvailability": 5,
	  "instructor": "Ranveer Singh Chandok"
	}
]

jsonDataUni = JSON.stringify(dataUni)