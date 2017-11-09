#region Using Statements
using System;

using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Storage;
using Microsoft.Xna.Framework.Input;
using C3.XNA;

#endregion

namespace PlanetSimulation
{
	/// <summary>
	/// This is the main type for your game
	/// </summary>
	public class Game1 : Game
	{
		Camera2d cam;

		PlanetManager pManager;

		GraphicsDeviceManager graphics;
		SpriteBatch spriteBatch;

		public Game1 ()
		{
			graphics = new GraphicsDeviceManager (this);
			Content.RootDirectory = "Content";	            
			graphics.IsFullScreen = true;		
			//graphics.ApplyChanges();
		}

		/// <summary>
		/// Allows the game to perform any initialization it needs to before starting to run.
		/// This is where it can query for any required services and load any non-graphic
		/// related content.  Calling base.Initialize will enumerate through any components
		/// and initialize them as well.
		/// </summary>
		protected override void Initialize ()
		{
			// TODO: Add your initialization logic here

			pManager = new PlanetManager();
			base.Initialize ();
			cam = new Camera2d();
				
		}

		/// <summary>
		/// LoadContent will be called once per game and is the place to load
		/// all of your content.
		/// </summary>
		protected override void LoadContent ()
		{
			// Create a new SpriteBatch, which can be used to draw textures.
			spriteBatch = new SpriteBatch (GraphicsDevice);

			//TODO: use this.Content to load your game content here 
		}

		/// <summary>
		/// Allows the game to run logic such as updating the world,
		/// checking for collisions, gathering input, and playing audio.
		/// </summary>
		/// <param name="gameTime">Provides a snapshot of timing values.</param>
		protected override void Update (GameTime gameTime)
		{
			// For Mobile devices, this logic will close the Game when the Back button is pressed
			if (GamePad.GetState (PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape)) {
				Exit ();
			}

			if(Keyboard.GetState().IsKeyDown(Keys.OemMinus))
				cam.Zoom -= 0.025f;
			if(Keyboard.GetState().IsKeyDown(Keys.OemPlus))
				cam.Zoom += 0.025f;

			if(Keyboard.GetState().IsKeyDown(Keys.Down)) 
				cam.Pos = Vector2.Add(cam.Pos, new Vector2(0,cam.Zoom * 20));
			if(Keyboard.GetState().IsKeyDown(Keys.Up))
				cam.Pos = Vector2.Add(cam.Pos, new Vector2(0,-cam.Zoom * 20));
			if(Keyboard.GetState().IsKeyDown(Keys.Right))
				cam.Pos = Vector2.Add(cam.Pos, new Vector2(cam.Zoom * 20 ,0));
			if(Keyboard.GetState().IsKeyDown(Keys.Left))
				cam.Pos = Vector2.Add(cam.Pos, new Vector2(-cam.Zoom * 20 ,0));

			pManager.Update(gameTime);
			// TODO: Add your update logic here			
			base.Update (gameTime);
		}

		/// <summary>
		/// This is called when the game should draw itself.
		/// </summary>
		/// <param name="gameTime">Provides a snapshot of timing values.</param>
		protected override void Draw (GameTime gameTime)
		{
			graphics.GraphicsDevice.Clear (Color.Black);
			//TODO: Add your drawing code here
			spriteBatch.Begin(SpriteSortMode.BackToFront, BlendState.AlphaBlend, null, null, null, null,
                        cam.get_transformation(GraphicsDevice /*Send the variable that has your graphic device here*/));
		
			pManager.Draw(spriteBatch);
			spriteBatch.End();
			base.Draw (gameTime);
		}
	}
}

