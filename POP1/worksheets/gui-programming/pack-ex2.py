# Demo of Packing Vertically 
# -------------------
# Code for exercise 3

from tkinter import *
import random

app = Tk() # Create the top-level window
app.title("Pack Example") 

#
# Create three labels of given width
#
bA = Label(app, text="A", width=12, bg='red')
bB = Label(app, text="B", width=12, bg='yellow')
bC = Label(app, text="C", width=12, bg='blue')

# Pack vertically
# -----------------
# Vertical packing with 
#   side = "top"
#   side = "bottom"
bA.pack(side='top')
bB.pack(side='top')
bC.pack(side='top')

app.mainloop() # Start the main loop
