# CODE for EXERCISE 6
# -------------------
# This exercise introduces
#   * The radio button widget
#

from tkinter import *

root = Tk()

def sel():
   label["text"] = "You selected the option " + var.get()
   label["background"] = var.get()

var = StringVar()

for colour in ["Red", "Green", "Blue"]:
    rb = Radiobutton(root, text=colour, variable=var, value=colour.lower(),
                     command=sel)
    rb.pack(anchor = W)

label = Label(root)
label.pack()

var.set("red")
sel()

# Start the main loop
root.mainloop()
