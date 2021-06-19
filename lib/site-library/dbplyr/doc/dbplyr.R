<<<<<<< HEAD
## ---- include = FALSE----------------------------------------------------
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")
options(tibble.print_min = 6L, tibble.print_max = 6L, digits = 3)

## ---- eval = FALSE-------------------------------------------------------
#  install.packages("dbplyr")

## ----setup, message = FALSE----------------------------------------------
library(dplyr)
con <- DBI::dbConnect(RSQLite::SQLite(), path = ":memory:")

## ---- eval = FALSE-------------------------------------------------------
#  con <- DBI::dbConnect(RMySQL::MySQL(),
=======
## ---- include = FALSE---------------------------------------------------------
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")
options(tibble.print_min = 6L, tibble.print_max = 6L, digits = 3)

## ---- eval = FALSE------------------------------------------------------------
#  install.packages("dbplyr")

## ----setup, message = FALSE---------------------------------------------------
library(dplyr)
con <- DBI::dbConnect(RSQLite::SQLite(), dbname = ":memory:")

## ---- eval = FALSE------------------------------------------------------------
#  con <- DBI::dbConnect(RMariaDB::MariaDB(),
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#    host = "database.rstudio.com",
#    user = "hadley",
#    password = rstudioapi::askForPassword("Database password")
#  )

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
copy_to(con, nycflights13::flights, "flights",
  temporary = FALSE, 
  indexes = list(
    c("year", "month", "day"), 
    "carrier", 
    "tailnum",
    "dest"
  )
)

<<<<<<< HEAD
## ------------------------------------------------------------------------
flights_db <- tbl(con, "flights")

## ------------------------------------------------------------------------
flights_db 

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
flights_db <- tbl(con, "flights")

## -----------------------------------------------------------------------------
flights_db 

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
flights_db %>% select(year:day, dep_delay, arr_delay)

flights_db %>% filter(dep_delay > 240)

flights_db %>% 
  group_by(dest) %>%
  summarise(delay = mean(dep_time))

<<<<<<< HEAD
## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
tailnum_delay_db <- flights_db %>% 
  group_by(tailnum) %>%
  summarise(
    delay = mean(arr_delay),
    n = n()
  ) %>% 
  arrange(desc(delay)) %>%
  filter(n > 100)

<<<<<<< HEAD
## ------------------------------------------------------------------------
tailnum_delay_db

## ------------------------------------------------------------------------
tailnum_delay_db %>% show_query()

## ------------------------------------------------------------------------
tailnum_delay <- tailnum_delay_db %>% collect()
tailnum_delay

## ---- error = TRUE-------------------------------------------------------
=======
## -----------------------------------------------------------------------------
tailnum_delay_db

## -----------------------------------------------------------------------------
tailnum_delay_db %>% show_query()

## -----------------------------------------------------------------------------
tailnum_delay <- tailnum_delay_db %>% collect()
tailnum_delay

## ---- error = TRUE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
nrow(tailnum_delay_db)

tail(tailnum_delay_db)

