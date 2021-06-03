library(httr)

# 1. Find OAuth settings for linkedin:
#    https://developer.linkedin.com/documents/linkedins-oauth-details
<<<<<<< HEAD
oauth_endpoints("linkedin")
=======
endpoints <- oauth_endpoints("linkedin")
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

# 2. Register an application at https://www.linkedin.com/secure/developer
#    Make sure to register http://localhost:1410/ as an "OAuth 2.0 Redirect URL".
#    (the trailing slash is important!)
#
#    Replace key and secret below.
myapp <- oauth_app("linkedin",
  key = "outmkw3859gy",
<<<<<<< HEAD
  secret = "n7vBr3lokGOCDKCd")

# 3. Get OAuth credentials
# LinkedIn doesn't implement OAuth 2.0 standard
# (http://tools.ietf.org/html/rfc6750#section-2) so we extend the Token2.0
# ref class to implement a custom sign method.
TokenLinkedIn <- R6::R6Class("TokenLinkedIn", inherit = Token2.0, list(
  sign = function(method, url) {
    url <- parse_url(url)
    url$query$oauth2_access_token <- self$credentials$access_token
    request(url = build_url(url))
  },
  can_refresh = function() {
    TRUE
  },
  refresh = function() {
    self$credentials <- init_oauth2.0(self$endpoint, self$app,
      scope = self$params$scope, type = self$params$type,
      use_oob = self$params$use_oob)
  }
))
token <- TokenLinkedIn$new(
  endpoint = oauth_endpoints("linkedin"),
  app = myapp,
  params = list(use_oob = FALSE, scope = NULL, type = NULL)
)

# 4. Use API
req <- GET("https://api.linkedin.com/v1/people/~", config(token = token))
=======
  secret = "n7vBr3lokGOCDKCd"
)

# 3. Get OAuth credentials and specify a scope your app has permission for
token <- oauth2.0_token(endpoints, myapp, scope = "r_liteprofile")

# 4. Use API
req <- GET("https://api.linkedin.com/v2/me", config(token = token))
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
stop_for_status(req)
content(req)
