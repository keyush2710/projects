import React from 'react';

function Schedule() {
  return (
    <div className="Schedule">
      <h1>Tution Class Timings</h1>
      <section id="days">
      <div>
        <h3>Monday</h3>
        <ul>
            <li>9:30 AM to 11:30 AM</li>
            <li>3:00 PM to 5:00 PM</li>
        </ul>
      </div>
      <div>
        <h3>Tuesday</h3>
        <ul>
            <li>10:30 AM to 12:30 pM</li>
            <li>4:00 PM to 6:00 PM</li>
        </ul>
      </div>
      <div>
        <h3>Wednesday</h3>
        <ul>
            <li>9:30 AM to 11:30 AM</li>
            <li>3:00 PM to 5:00 PM</li>
        </ul>
      </div>
      <div>
        <h3>Thursday</h3>
        <ul>
            <li>10:30 AM to 12:30 pM</li>
            <li>4:00 PM to 6:00 PM</li>
        </ul>
      </div>
      <div>
        <h3>Friday</h3>
        <ul>
            <li>9:30 AM to 11:30 AM</li>
            <li>3:00 PM to 5:00 PM</li>
        </ul>
      </div>
      </section>
    </div>
  );
}

export default Schedule;