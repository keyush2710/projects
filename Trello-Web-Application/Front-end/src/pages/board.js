import React from 'react';
import './board.css';
import { useState,useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { styled, ThemeProvider, createTheme } from '@mui/system';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import TextField from '@mui/material/TextField';
import { AiOutlineDelete } from 'react-icons/ai';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Grid from '@mui/material/Grid';
import { Box } from '@mui/system';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import MenuItem from '@mui/material/MenuItem';
import { FormControl, InputLabel, Select } from '@mui/material';
import { isWithinInterval, isSameDay } from 'date-fns';




const BoardContainer = styled(Card)(({ theme }) => ({
  width: '300px',
  height: 'fit-content',
  padding: '10px',
}));

const Board = () => {
    const navigate = useNavigate();
  //  const [boardId,setBoardId]=useState('') ;
    const [message, setMessage] = useState('');
    const {workspaceId}=useParams();
   const {boardId}=useParams();
    useEffect(() => {
      // Get the value from local storage using its key (e.g., 'myValueKey')
      const storedValueFromLocalStorage = localStorage.getItem('boardId');
  
      // Update the state variable with the value from local storage
      // if (storedValueFromLocalStorage) {
      //   setBoardId(storedValueFromLocalStorage);
      // }
    }, []);

    const [members, setMembers] = useState([
    ]);
  
  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async() => {
    try{
    const response = await fetch(`http://localhost:8080/workspaces/getAllMembers?id=${workspaceId}`,{
      method: 'GET',
    });
      if(response.ok) {
        const membersData = await response.json(); // Parse the response body as JSON
        console.log(membersData);
        const updatedLists = [];
        membersData.map((member,index) => {
        updatedLists.push({
          id:index,
          name:member,
        });

        console.log(updatedLists);
        setMembers(updatedLists);
      });
  }
}
      catch(error){
        console.error('Error fetching Members:', error);
      }
    };

    const [lists, setLists] = useState([
      { id: 'list-1', title: 'To Do', cards: [] },
      { id: 'list-2', title: 'Doing', cards: [] },
      { id: 'list-3', title: 'Done', cards: [] },
    ]);
    useEffect(() => {
      // Fetch the list of workspaces from the backend on component mount
      fetchTasks();
    }, []);
    const fetchTasks = async () => {
      try {
        const response = await fetch(`http://localhost:8080/task/allTasks?boardId=${boardId}&workspaceId=${workspaceId}`, {
          method: 'GET',
        });
  
        if (response.ok) {
          const tasksData = await response.json();
          console.log(tasksData);
          const updatedLists = [
            { id: 'list-1', title: 'To Do', cards: [] },
            { id: 'list-2', title: 'Doing', cards: [] },
            { id: 'list-3', title: 'Done', cards: [] },
          ];

          tasksData.map((task) => {
            // Convert the dueDate to a normal date
            console.log(task.taskStatus);
            let normalDate = new Date(task.dueDate);
            if(task.dueDate==null)
            {
              normalDate=null;
            }
            let names;
            let bid;
            if(task.user==null)
            {
              names="";
              bid=0;
            }
            else{
              names=task.user.firstName;
              bid=task.user.id;

            }
            if(task.taskStatus=="TODO"){
            // Push the task data to the appropriate list            
            updatedLists[0].cards.push({
              id: task.taskId,
              title: task.taskName,
              dueDate: normalDate,
              member: {id: bid, name:names}
            });
            console.log("helop1",updatedLists[0].cards[0].member);
          }

          if(task.taskStatus=="DOING"){

            console.log(normalDate);
            // Push the task data to the appropriate list
            console.log(task.status);
            updatedLists[1].cards.push({
              id: task.taskId,
              title: task.taskName,
              dueDate: normalDate,
              member: {id: bid, name:names},
            });
          }
          if(task.taskStatus=="DONE"){

            console.log(normalDate);
            // Push the task data to the appropriate list
            console.log(task.status);
            updatedLists[2].cards.push({
              id: task.taskId,
              title: task.taskName,
              dueDate: normalDate,
               member: {id: bid, name:names},
            });
          }
          });
  
          setLists(updatedLists);
          console.log(updatedLists);
        } else {
          console.error('Error fetching tasks:', response.status);
        }
      } catch (error) {
        console.error('Error fetching tasks:', error);
      }
    };
 

    const handleDeleteBoard =async () => {
      try {
        const response = await fetch(`http://localhost:8080/boards/delete?boardID=${boardId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            boardID:boardId,
          }),

        });
  
        if (response.ok) {
          alert('Board deleted successfully');
          navigate('/workspaces/'+workspaceId+'/boardhome');
        } else {
          const error = await response.text();
          alert(`Failed to delete board: {BOARD IS NOT EMPTY}`);
        }
      } catch (error) {
        console.error('Error deleting board:', error);
        setMessage('An error occurred while deleting the board');
      }
      
    };
    // const handleMoveCard = (sourceListIndex, sourceCardIndex, targetListIndex, targetCardIndex) => {
    //   const updatedLists = [...lists];
    //   const [movedCard] = updatedLists[sourceListIndex].cards.splice(sourceCardIndex, 1);
    //   updatedLists[targetListIndex].cards.splice(targetCardIndex, 0, movedCard);
    //   setLists(updatedLists);
    // };
  
    const handleMoveCard =async (sourceListIndex, sourceCardIndex, targetListIndex, targetCardIndex) => {
      const movedCard = lists[sourceListIndex].cards[sourceCardIndex];
      let newTaskStatus;
      if (targetListIndex === 1) {
       newTaskStatus = "DOING";
      } else if (targetListIndex === 2) {
      newTaskStatus = "DONE";
      } else {
       newTaskStatus = "TODO";
    }
      
     try{
     const response=await fetch(`http://localhost:8080/task/changeStatus?taskId=${movedCard.id}&status=${newTaskStatus}`, {
        method: 'PUT', // Assuming your backend supports a PATCH request to update the card
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(
          { 
            taskId:movedCard.id,
            status:newTaskStatus,
          }),
      });
      if(response.ok){
        // Update the frontend state with the new data received from the backend
        console.log("good");
        console.log(targetListIndex);
        const updatedLists = [...lists];
          const [movedCard] = updatedLists[sourceListIndex].cards.splice(sourceCardIndex, 1);
          updatedLists[targetListIndex].cards.splice(targetCardIndex, 0, movedCard);
          setLists(updatedLists);
      }
    }
      catch (error) {
          // Handle error, e.g., show an error message to the user
          console.error('Error moving card:', error);
        };
    };

    const handleDeleteCard = (listIndex, cardIndex) => {
      const updatedLists = [...lists];
      updatedLists[listIndex].cards.splice(cardIndex, 1);
      setLists(updatedLists);
    };
  
    // const handleAddCard = (listIndex, event) => {
    //   event.preventDefault();
    //   const input = event.target.elements.cardTitle;
    //   const title = input.value.trim();
    //   if (title !== '') {
    //     const updatedLists = [...lists];
    //     const selectedMemberId = input.value;
    //     const selectedMember = members.find((member) => member.id === selectedMemberId);
    //     updatedLists[listIndex].cards.push({
    //       id: `card-${Date.now()}`,
    //       title,
    //       dueDate: null,
    //       member: selectedMember,
    //     });
    //     setLists(updatedLists);
    //     input.value = '';
    //   }
    // };

    const handleAddCard = async (listIndex, event) => {
      event.preventDefault();
      const input = event.target.elements.cardTitle;
      const title = input.value.trim();
      let taskName=title;
      let taskStatus;
      //setTaskName(title);
      if (listIndex === 0) {
        taskStatus="TODO";
       // setTaskStatus("TODO");
      }
      if (listIndex === 1) {
        taskStatus="DOING";
       // setTaskStatus("TODO");
      }
      if (listIndex === 2) {
        taskStatus="DONE";
       // setTaskStatus("TODO");
      }
      let dueDate="";
     // setDueDate("2023-07-31");
      console.log(boardId);

      try {
        const response = await fetch(`http://localhost:8080/task/save?boardId=${boardId}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            taskName,
            taskStatus,
            dueDate,
            boardId: boardId,
            "user":null
          }),
        });
    
        if (response.ok) {
          const data = await response.json(); // Parse the response body as JSON
          console.log("success");
          console.log(data);
          //console.log(input.value);
          const selectedMemberId = input.value;
        const selectedMember = members.find((member) => member.id === selectedMemberId);
          // Create a copy of the lists state
          const updatedLists = [...lists];
    
          // Push the new card to the appropriate list
          updatedLists[listIndex].cards.push({
          id: data.taskId, // Use the ID received from the backend
          title: data.taskName, // Use the taskName received from the backend
          dueDate: null, // Use the dueDate received from the backend
          member: selectedMember,});
    
          // Update the state with the new lists data
          setLists(updatedLists);
    
          input.value = '';
        } else {
          throw new Error('Failed to add card'); // Throw an error to trigger the catch block
        }
      } catch (error) {
        // Handle error, e.g., show an error message to the user
        console.error('Error adding card:', error);
      }
    };

    const handleDragEnd = (result) => {
      const { source, destination } = result;
      if (!destination) return;
  
      const sourceListIndex = parseInt(source.droppableId.replace('list-', ''));
      const sourceCardIndex = source.index;
      const targetListIndex = parseInt(destination.droppableId.replace('list-', ''));
      const targetCardIndex = destination.index;
  
      handleMoveCard(sourceListIndex, sourceCardIndex, targetListIndex, targetCardIndex);
    };
  
    const [searchQuery, setSearchQuery] = useState('');
    const [filterOption, setFilterOption] = useState('all');
  
    const handleSearch = (event) => {
      setSearchQuery(event.target.value);
    };
  
    const handleFilter = (event) => {
      setFilterOption(event.target.value);
    };
  
    // const handleDateChange = (listIndex, cardIndex, date) => {
    //   const updatedLists = [...lists];
    //   updatedLists[listIndex].cards[cardIndex].dueDate = date;
    //   setLists(updatedLists);
    // };
 
    const handleDateChange =async (listIndex, cardIndex, date) => {
      const cardToUpdate = lists[listIndex].cards[cardIndex];
    console.log(date);
  const formattedDate = date.toISOString().slice(0, 10);
  console.log(formattedDate);
  try{
  const response = await fetch(`http://localhost:8080/task/changeDueDate?taskId=${cardToUpdate.id}&newDueDate=${formattedDate}`, {
        method: 'PUT', // Assuming your backend supports a PATCH request to update the card
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(
        { 
          taskId: cardToUpdate.id,
          dueDate: formattedDate,
         }),
      });
        if(response.ok){
          // Update the frontend state with the new data received from the backend
          const updatedLists = [...lists];
          updatedLists[listIndex].cards[cardIndex].dueDate=date;
          setLists(updatedLists);
        }
      }
        catch(error) {
          // Handle error, e.g., show an error message to the user
          console.error('Error updating due date:', error);
        };
    };
  
    // const handleAssignMember = (listIndex, cardIndex, memberId) => {
    //   const updatedLists = [...lists];
    //   const selectedMember = members.find((member) => member.id === memberId);
    //   updatedLists[listIndex].cards[cardIndex].member = selectedMember;
    //   setLists(updatedLists);
    // };

    const handleAssignMember = async (listIndex, cardIndex, memberId) => {
      const memberIdAsInt = parseInt(memberId, 10)-1;
      const cardToUpdate = lists[listIndex].cards[cardIndex];
      if (!memberId) {
        // Show an error or take appropriate action if the member is not selected
        console.log('No member selected');
        return;
      }
     
      // Assuming your members have a unique 'id' field, you can directly use memberId as follows:
      const selectedMember = members[memberIdAsInt];
      console.log("helo",members);
    console.log(selectedMember);
      try {
        const response = await fetch(`http://localhost:8080/task/assignMember?userId=${memberId}&taskId=${cardToUpdate.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            taskId: cardToUpdate.id,
            userId: memberId,
          }),
        });
    
        if (response.ok) {
          // Update the frontend state with the new data received from the backend
          const updatedLists = [...lists];
          console.log(updatedLists[listIndex].cards[cardIndex]);
          updatedLists[listIndex].cards[cardIndex].member = selectedMember;
          setLists(updatedLists);
        } else {
          throw new Error('Failed to assign member'); // Throw an error to trigger the catch block
        }
      } catch (error) {
        // Handle error, e.g., show an error message to the user
        console.error('Error assigning member:', error);
      }
    };
    // const filteredLists = lists.map((list) => ({
    //   ...list,
    //   cards: list.cards.filter((card) =>
    //     card.title.toLowerCase().includes(searchQuery.toLowerCase())
    //   ),
    // }));
  
    // if (filterOption !== 'all') {
    //   const today = new Date();
    //   const filteredDateLists = filteredLists.map((list) => ({
    //     ...list,
    //     cards: list.cards.filter((card) =>
    //       filterOption === 'overdue'
    //         ? card.dueDate && card.dueDate < today
    //         : card.dueDate === filterOption
    //     ),
    // //   }));
  
    //   filteredLists.splice(0, filteredLists.length, ...filteredDateLists);

    const filterOptions = [
      { value: 'all', label: 'All' },
      { value: 'due-today', label: 'Due Today' },
      { value: 'due-this-week', label: 'Due This Week' },
      { value: 'no-due-date', label: 'No Due Date' },
    ];
  
    // Filter tasks based on the selected filter option and search query
    const filteredLists = lists.map((list) => {
      if (filterOption === 'all') {
        return { ...list, cards: list.cards.filter((card) => card.title.toLowerCase().includes(searchQuery.toLowerCase())) };
      }
  
      const today = new Date();
      const oneWeekFromToday = new Date(today);
      oneWeekFromToday.setDate(today.getDate() + 7);
  
      return {
        ...list,
        cards: list.cards.filter((card) => {
          if (filterOption === 'due-today') {
            return card.dueDate && isSameDay(card.dueDate, today);
          }
          if (filterOption === 'due-this-week') {
            return card.dueDate && isWithinInterval(card.dueDate, { start: today, end: oneWeekFromToday });
          }
          if (filterOption === 'no-due-date') {
            return !card.dueDate;
          }
          return true;
        }),
      };
    });
  
    
  return (
    <ThemeProvider>
      <div className={BoardContainer}>
        <Typography variant="h4" component="h1" className="boardTitle" sx={{ fontFamily: 'TimesNewRoman', textAlign: 'center' }}>
          My Board
        </Typography>

        <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginBottom: 2 }}>
          <Button onClick={handleDeleteBoard} variant="outlined" color="secondary" sx={{ backgroundColor: 'red', color: 'black' }}>
            Delete Board
          </Button>
        </Box>
        <Box sx={{ display: 'block', marginLeft: '20px', marginBottom: 2, fontWeight:'bold'}}>
            <div>
              <a href="./workspace" style={{ textDecoration:'none', color: 'green'}}>Go to Workspace</a>
            </div>
        </Box>
        
        {/* <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginBottom: 2 }}>
          <TextField
            value={searchQuery}
            onChange={handleSearch}
            label="Search Task"
            variant="outlined"
            sx={{ width: '250px', marginRight: '16px' }}
          />
          <FormControl variant="outlined" sx={{ minWidth: '150px' }}>
            <InputLabel>Filter by Due Date</InputLabel>
            <Select value={filterOption} onChange={handleFilter} label="Filter by Due Date">
              <MenuItem value="all">All</MenuItem>
              <MenuItem value="overdue">Overdue</MenuItem>
              <MenuItem value={null}>No Due Date</MenuItem>
            </Select>
          </FormControl>
        </Box> */}

<Box sx={{ display: 'flex', justifyContent: 'flex-end', marginBottom: 2 }}>
          <TextField
            value={searchQuery}
            onChange={handleSearch}
            label="Search Task"
            variant="outlined"
            sx={{ width: '250px', marginRight: '16px' }}
          />
          <FormControl variant="outlined" sx={{ minWidth: '150px' }}>
            <InputLabel>Filter by Due Date</InputLabel>
            <Select value={filterOption} onChange={handleFilter} label="Filter by Due Date">
              {filterOptions.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>


        <Grid container spacing={2} sx={{ display: 'flex', justifyContent: 'space-evenly' }}>
          <DragDropContext onDragEnd={handleDragEnd}>
            {filteredLists.map((list, listIndex) => (
              <Grid item xs={12} sm={6} md={4} lg={3} key={list.id}>
                <Droppable droppableId={`list-${listIndex}`}>
                  {(provided) => (
                    <Card className="cardWrapper" ref={provided.innerRef} {...provided.droppableProps}>
                      <CardContent>
                        <Typography variant="h6" component="h2">
                          {list.title}
                        </Typography>
                        {list.cards.map((card, cardIndex) => (
                          <Draggable key={card.id} draggableId={`card-${card.id}`} index={cardIndex}>
                            {(provided) => (
                              <ListItem
                                ref={provided.innerRef}
                                {...provided.draggableProps}
                                {...provided.dragHandleProps}
                                sx={{ display: 'contents', alignItems: 'center', marginBottom: 1, '& .MuiListItemText-root': { flexGrow: 1 }, }}
                              >
                                <ListItemText primary={card.title} primaryTypographyProps={{ sx: { flexShrink: 1, textOverflow: 'ellipsis', whiteSpace: 'nowrap', } }} />
                                <Button onClick={() => handleMoveCard(listIndex, cardIndex, (listIndex + 1) % lists.length)} variant="outlined" color="primary" sx={{ fontSize: '12px', padding: '4px' }}>
                                  Move
                                </Button>
                                <AiOutlineDelete onClick={() => handleDeleteCard(listIndex, cardIndex)} style={{ cursor: 'pointer', color: 'red' }} />
                                <div style={{ width: '90px', display: 'inline-block' }}>
                                  <DatePicker
                                    selected={card.dueDate}
                                    onChange={(date) => handleDateChange(listIndex, cardIndex, date)}
                                    dateFormat="MM/dd/yyyy"
                                    placeholderText="Select a due date"
                                  />
                                </div>
                                    {card.member ? (
                                  <div>{card.member.name}</div>
                                ) : (
                                  <div>
                                    <TextField
                                      select
                                      value={card.member ? card.member.id : ''}
                                      onChange={(event) => handleAssignMember(listIndex, cardIndex, event.target.value)}
                                      variant="outlined"
                                      sx={{ width: '150px', marginLeft: '8px', visibility: card.member ? 'hidden' : 'visible' }}
                                      size='small'
                                    >
                                      <MenuItem value="">Assign a member</MenuItem>
                                      {members.map((member,index) => (
                                        <MenuItem key={index} value={index+1}>
                                          {member.name}
                                        </MenuItem>
                                      ))}
                                    </TextField>
                                  </div>
                                )}
                              </ListItem>
                            )}
                          </Draggable>
                        ))}
                        {provided.placeholder}
                        <form onSubmit={(event) => handleAddCard(listIndex, event)}>
                          <TextField
                            name="cardTitle"
                            label="Add a new task"
                            variant="outlined"
                            size='small'
                            className="addButton"
                            sx={{
                              width: '150px',
                              maxWidth: '150px',
                              marginBottom: '3px',
                              marginTop: '10px',
                            }}
                          />
                          <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                            className="addButton"
                            sx={{
                              height: '32px',
                              marginLeft: '8px',
                            }}
                          >
                            Add Card
                          </Button>
                        </form>
                      </CardContent>
                    </Card>
                  )}
                </Droppable>
              </Grid>
            ))}
          </DragDropContext>
        </Grid>
      </div>
    </ThemeProvider>

  );
};

export default Board;
