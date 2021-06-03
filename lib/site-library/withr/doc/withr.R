<<<<<<< HEAD
## ----setup, include = FALSE----------------------------------------------
=======
## ----setup, include = FALSE---------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
knitr::opts_chunk$set(
  collapse = TRUE,
  comment = "#>"
)
library(withr)

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par("col" = "black")
my_plot <- function(new) {
  old <- par(col = "red", pch = 19)
  plot(mtcars$hp, mtcars$wt)
  par(old)
}
my_plot()
par("col")

<<<<<<< HEAD
## ---- error = TRUE-------------------------------------------------------
=======
## ---- error = TRUE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par("col" = "black")
my_plot <- function(new) {
  old <- par(col = "red", pch = 19)
  plot(mtcars$hpp, mtcars$wt)
  par(old)
}
my_plot()
par("col")

<<<<<<< HEAD
## ---- error = TRUE-------------------------------------------------------
=======
## ---- error = TRUE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par("col" = "black")
my_plot <- function(new) {
  old <- par(col = "red", pch = 19)
  on.exit(par(old))
  plot(mtcars$hpp, mtcars$wt)
}
my_plot()
par("col")

options(test = 1)
{
  print(getOption("test"))
  on.exit(options(test = 2))
}
getOption("test")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par("col" = "black")
my_plot <- function(new) {
  with_par(list(col = "red", pch = 19),
    plot(mtcars$hp, mtcars$wt)
  )
  par("col")
}
my_plot()
par("col")

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
par("col" = "black")
my_plot <- function(new) {
  local_par(list(col = "red", pch = 19))
  plot(mtcars$hp, mtcars$wt)
}
my_plot()
par("col")

