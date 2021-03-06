using System;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace UI
{
public class SolidColorTexture : Texture2D
    {
        private Color _color;
        // Gets or sets the color used to create the texture
        public Color Color
        {
            get { return _color; }
            set
            {
                if (value != _color)
                {
                    _color = value;
                    SetData<Color>(new Color[] { _color });
                }
            }
        }


        public SolidColorTexture(GraphicsDevice graphicsDevice)
            : base(graphicsDevice, 1, 1)
        {
            //default constructor
        }
        public SolidColorTexture(GraphicsDevice graphicsDevice, Color color)
            : base(graphicsDevice, 1, 1)
        {
            Color = color;
        }

    }
}

