using Godot;
using System;


public class Movement:KinematicBody2D

{
	public int maxspeed = 500;
	public int maxsprint =750;
	public int acceleration = 10000;
	public int friction  = 10000;
	
	Vector2 playervelocity;
	
	public override void _Ready()
	{
		GD.Print("Hello World!");
		playervelocity.x= 0;
		playervelocity.y= 0;
		//var animationPlayer = $AnimationPlayer;
	}
	
	public override void _Process(float delta)
	{
		var input_vector = Vector2.Zero;
		
		input_vector.x = Input.GetActionStrength("right")-Input.GetActionStrength("left");
		input_vector.y = Input.GetActionStrength("up")-Input.GetActionStrength("down");
		input_vector = input_vector.Normalized();
		
		if (input_vector != Vector2.Zero)
		{
			if(Input.GetActionStrength("sprint") == 1)
			{
				playervelocity += input_vector * acceleration*delta;
				playervelocity = playervelocity.Clamped(length: maxsprint);
			}
			else
			{
				playervelocity += input_vector * acceleration*delta;
				playervelocity = playervelocity.Clamped(length: maxspeed);
			}
			
		}
		else
		{
			playervelocity = playervelocity.MoveToward(Vector2.Zero, friction * delta);
		}
		
		GD.Print(playervelocity);
		
		
		playervelocity = MoveAndSlide(playervelocity);
	}
}
