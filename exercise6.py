# CODE for EXERCISE 6
# -------------------
# This exercise introduces  
#   * The radio button widget
#

# MAKE THE FOLLOWING CHANGES
#   * change the text of the radio buttons to colours
#   * when the selection is made, change th background colour of the label
#

from tkinter import *

root = Tk()

# Method handles radio button selection
#    * Create a string showing which choice selected
#    * Use this in the label
def sel():
   selection = "You selected the option " + str(var.get())
   label["text"] = selection

# Integer control variable 
#   Holds the selection of the radio button 
var = IntVar()

# Create 2 radio buttons. Each has the same control variable
#
R1 = Radiobutton(root, text="Option 1", variable=var, value=1,
                  command=sel)
R1.pack( anchor = W )

R2 = Radiobutton(root, text="Option 2", variable=var, value=2,
                  command=sel)
R2.pack( anchor = W )


# Create a label. Intially blank
label = Label(root)
label.pack()

# Start the main loop
root.mainloop()
