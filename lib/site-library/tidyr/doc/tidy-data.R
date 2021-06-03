<<<<<<< HEAD
## ---- echo = FALSE-------------------------------------------------------
=======
## ---- echo = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
knitr::opts_chunk$set(collapse = TRUE, comment = "#>")
set.seed(1014)
options(dplyr.print_max = 10)

<<<<<<< HEAD
## ------------------------------------------------------------------------
preg <- read.csv("preg.csv", stringsAsFactors = FALSE)
preg

## ------------------------------------------------------------------------
read.csv("preg2.csv", stringsAsFactors = FALSE)

## ---- message = FALSE----------------------------------------------------
library(tidyr)
library(dplyr)
preg2 <- preg %>% 
  gather(treatment, n, treatmenta:treatmentb) %>%
  mutate(treatment = gsub("treatment", "", treatment)) %>%
  arrange(name, treatment)
preg2

## ------------------------------------------------------------------------
pew <- tbl_df(read.csv("pew.csv", stringsAsFactors = FALSE, check.names = FALSE))
pew

## ------------------------------------------------------------------------
pew %>%
  gather(income, frequency, -religion)

## ------------------------------------------------------------------------
billboard <- tbl_df(read.csv("billboard.csv", stringsAsFactors = FALSE))
billboard

## ------------------------------------------------------------------------
billboard2 <- billboard %>% 
  gather(week, rank, wk1:wk76, na.rm = TRUE)
billboard2

## ------------------------------------------------------------------------
billboard3 <- billboard2 %>%
  mutate(
    week = extract_numeric(week),
    date = as.Date(date.entered) + 7 * (week - 1)) %>%
  select(-date.entered)
billboard3

## ------------------------------------------------------------------------
billboard3 %>% arrange(artist, track, week)

## ------------------------------------------------------------------------
billboard3 %>% arrange(date, rank)

## ------------------------------------------------------------------------
tb <- tbl_df(read.csv("tb.csv", stringsAsFactors = FALSE))
tb

## ------------------------------------------------------------------------
tb2 <- tb %>% 
  gather(demo, n, -iso2, -year, na.rm = TRUE)
tb2

## ------------------------------------------------------------------------
=======
## -----------------------------------------------------------------------------
classroom <- read.csv("classroom.csv", stringsAsFactors = FALSE)
classroom

## -----------------------------------------------------------------------------
read.csv("classroom2.csv", stringsAsFactors = FALSE)

## ----setup, message = FALSE---------------------------------------------------
library(tidyr)
library(dplyr)

## -----------------------------------------------------------------------------
classroom2 <- classroom %>% 
  pivot_longer(quiz1:test1, names_to = "assessment", values_to = "grade") %>% 
  arrange(name, assessment)
classroom2

## -----------------------------------------------------------------------------
relig_income

## -----------------------------------------------------------------------------
relig_income %>% 
  pivot_longer(-religion, names_to = "income", values_to = "frequency")

## -----------------------------------------------------------------------------
billboard

## -----------------------------------------------------------------------------
billboard2 <- billboard %>% 
  pivot_longer(
    wk1:wk76, 
    names_to = "week", 
    values_to = "rank", 
    values_drop_na = TRUE
  )
billboard2

## -----------------------------------------------------------------------------
billboard3 <- billboard2 %>%
  mutate(
    week = as.integer(gsub("wk", "", week)),
    date = as.Date(date.entered) + 7 * (week - 1),
    date.entered = NULL
  )
billboard3

## -----------------------------------------------------------------------------
billboard3 %>% arrange(artist, track, week)

## -----------------------------------------------------------------------------
billboard3 %>% arrange(date, rank)

## -----------------------------------------------------------------------------
tb <- as_tibble(read.csv("tb.csv", stringsAsFactors = FALSE))
tb

## -----------------------------------------------------------------------------
tb2 <- tb %>% 
  pivot_longer(
    c(-iso2, -year), 
    names_to = "demo", 
    values_to = "n", 
    values_drop_na = TRUE
  )
tb2

## -----------------------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
tb3 <- tb2 %>% 
  separate(demo, c("sex", "age"), 1)
tb3

<<<<<<< HEAD
## ------------------------------------------------------------------------
weather <- tbl_df(read.csv("weather.csv", stringsAsFactors = FALSE))
weather

## ------------------------------------------------------------------------
weather2 <- weather %>%
  gather(day, value, d1:d31, na.rm = TRUE)
weather2

## ------------------------------------------------------------------------
weather3 <- weather2 %>% 
  mutate(day = extract_numeric(day)) %>%
  select(id, year, month, day, element, value) %>%
  arrange(id, year, month, day)
weather3

## ------------------------------------------------------------------------
weather3 %>% spread(element, value)

## ------------------------------------------------------------------------
song <- billboard3 %>% 
  select(artist, track, year, time) %>%
  unique() %>%
  mutate(song_id = row_number())
song

## ------------------------------------------------------------------------
rank <- billboard3 %>%
  left_join(song, c("artist", "track", "year", "time")) %>%
  select(song_id, date, week, rank) %>%
  arrange(song_id, date)
rank

## ---- eval = FALSE-------------------------------------------------------
=======
## -----------------------------------------------------------------------------
tb %>% pivot_longer(
  c(-iso2, -year), 
  names_to = c("sex", "age"), 
  names_pattern = "(.)(.+)",
  values_to = "n", 
  values_drop_na = TRUE
)


## -----------------------------------------------------------------------------
weather <- as_tibble(read.csv("weather.csv", stringsAsFactors = FALSE))
weather

## -----------------------------------------------------------------------------
weather2 <- weather %>% 
  pivot_longer(
    d1:d31, 
    names_to = "day", 
    values_to = "value", 
    values_drop_na = TRUE
  ) 
weather2

## -----------------------------------------------------------------------------
weather3 <- weather2 %>% 
  mutate(day = as.integer(gsub("d", "", day))) %>%
  select(id, year, month, day, element, value)
weather3

## -----------------------------------------------------------------------------
weather3 %>% 
  pivot_wider(names_from = element, values_from = value)

## -----------------------------------------------------------------------------
song <- billboard3 %>% 
  distinct(artist, track) %>%
  mutate(song_id = row_number())
song

## -----------------------------------------------------------------------------
rank <- billboard3 %>%
  left_join(song, c("artist", "track")) %>%
  select(song_id, date, week, rank)
rank

## ---- eval = FALSE------------------------------------------------------------
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#  library(purrr)
#  paths <- dir("data", pattern = "\\.csv$", full.names = TRUE)
#  names(paths) <- basename(paths)
#  map_dfr(paths, read.csv, stringsAsFactors = FALSE, .id = "filename")

