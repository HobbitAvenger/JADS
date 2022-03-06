using Godot;
using System;


public class Movement:KinematicBody2D

{
	public int maxSpeed = 500;
	public int maxSprint =750;
	public int acceleration = 10000;
	public int friction  = 10000;
	
	Vector2 playerVelocity;
	
	public override void _Ready()
	{
		GD.Print("Hello World!");
		playerVelocity.x = 0;
		playerVelocity.y = 0;
		//var animationPlayer = $AnimationPlayer;
	}
	
	public override void _Process(float delta)
	{
		var inputVector = Vector2.Zero;
		
		inputVector.x = Input.GetActionStrength("right") - Input.GetActionStrength("left");
		inputVector.y = Input.GetActionStrength("up") - Input.GetActionStrength("down");
		inputVector = inputVector.Normalized();
		
		if (inputVector != Vector2.Zero)
		{
			if (Input.GetActionStrength("sprint") == 1)
			{
				playerVelocity += inputVector * acceleration * delta;
				playerVelocity = playerVelocity.Clamped(length: maxSprint);
			}
			else
			{
				playerVelocity += inputVector * acceleration * delta;
				playerVelocity = playerVelocity.Clamped(length: maxSpeed);
			}
			
		}

		else
		{
			playerVelocity = playerVelocity.MoveToward(Vector2.Zero, friction * delta);
		}
		
		GD.Print(playerVelocity);
		
		
		playerVelocity = MoveAndSlide(playerVelocity);
	}
}
