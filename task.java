package database;

public class task
{
	private Integer arrivalTime = 0;
	private Integer taskDuration = 0;
	private Integer turnaroundTime = 0;
	private Integer responseTime = 0;
	private Boolean finished;
	private Integer counter;
	private Character character;
	private Integer startTime = 0;
	private Integer exitTime = 0;
	
	public task(Integer arrival, Integer duration, Integer turnaround, Integer response, Character taskCharacter, Integer start, Integer exit) 
	    {
			arrivalTime = arrival;
	        taskDuration = duration;
	        turnaroundTime = turnaround;
	        responseTime = response;
	        finished = false;
	        counter = taskDuration;
	        character = taskCharacter;
	        startTime = start;
	        exitTime = exit;
	    }
	
	public Integer getArrivalTime() 
	{
		return arrivalTime;
	}
	public void setArrivalTime(Integer arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}

	public Integer getTaskDuration()
	{
		return taskDuration;
	}

	public void setTaskDuration(Integer taskDuration)
	{
		this.taskDuration = taskDuration;
	}

	public Integer getTurnaroundTime()
	{
		return turnaroundTime;
	}

	public void setTurnaroundTime(Integer turnaroundTime)
	{
		this.turnaroundTime = turnaroundTime;
	}

	public Integer getResponseTime()
	{
		return responseTime;
	}

	public void setResponseTime(Integer responseTime)
	{
		this.responseTime = responseTime;
	}

	public Integer getCounter()
	{
		return counter;
	}

	public void setCounter(Integer counter)
	{
		this.counter = counter;
	}

	public Character getCharacter()
	{
		return character;
	}
	public void setCharacter(Character character)
	{
		this.character = character;
	}

	public Boolean getFinished()
	{
		return finished;
	}

	public void setFinished(Boolean finished)
	{
		this.finished = finished;
	}

	public Integer getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Integer startTime)
	{
		this.startTime = startTime;
	}

	public Integer getExitTime()
	{
		return exitTime;
	}

	public void setExitTime(Integer exitTime)
	{
		this.exitTime = exitTime;
	}
}
