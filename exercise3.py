# CODE for EXERCISE 3
# -------------------
# This exercise introduces  
#   * Difference border styles
#   * Pack options: side, fill, expand
#
# The aim of this exercise is to explore layout

from tkinter import *
import random

app = Tk() # Create the top-level window
app.title("GUI Example 3") # OPTIONALLY set the title

# Borders and Background (many widgets, including Frames)
# ----------------------
# bd - border width
# Relief - border style (FLAT, RAISED, GROOVE, SUNKEN, RIDGE)
#        - FLAT (default) no border shows
#
# bg - background colour
#
bA = Label(app, text="A", width=12, bg='red', relief=GROOVE, bd=5)
bB = Label(app, text="B", width=12, bg='yellow')
bC = Label(app, text="C", width=12, bg='blue')
bD = Label(app, text="D", width=12, bg='white')

# Pack arguments
# ---------------
#
# Fill: does the widget fill the space given to it
#    fill=Y
#    fill=X
#    fill=BOTH
#
# Expand: is more space give to widget, by its parent?
#    expand=0 (default) no epxansion
#    expand=1 expand - number gives relative expansion 
bA.pack(side='top',fill=X, expand=1)
bB.pack(side='bottom')
bC.pack(side='left', fill=Y, expand=1)
bD.pack(side='right')


app.mainloop() # Start the main loop
