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
lF = Frame(app, bd=1, relief=RAISED)
bA = Label(lF, text="A", width=12, bg='blue')
bB = Label(lF, text="B", width=12, bg='white')

rF = Frame(app, bd=1, relief=RAISED)
bC = Label(rF, text="C", width=12, bg='white')
bD = Label(rF, text="D", width=12, bg='blue')

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

lF.pack(side="left", fill=BOTH, expand=1)
bA.pack(side='top', fill=BOTH, expand=1)
bB.pack(side='bottom', fill=BOTH, expand=1)

rF.pack(side="right", fill=BOTH, expand=1)
bC.pack(side='top', fill=BOTH, expand=1)
bD.pack(side='bottom', fill=BOTH, expand=1)


app.mainloop() # Start the main loop
