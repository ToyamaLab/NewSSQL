//
// detail/string_view.hpp
// ~~~~~~~~~~~~~~~~~~~~~~
//
<<<<<<< HEAD
// Copyright (c) 2003-2017 Christopher M. Kohlhoff (chris at kohlhoff dot com)
=======
// Copyright (c) 2003-2019 Christopher M. Kohlhoff (chris at kohlhoff dot com)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
//
// Distributed under the Boost Software License, Version 1.0. (See accompanying
// file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
//

#ifndef BOOST_ASIO_DETAIL_STRING_VIEW_HPP
#define BOOST_ASIO_DETAIL_STRING_VIEW_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <boost/asio/detail/config.hpp>

<<<<<<< HEAD
#if defined(BOOST_ASIO_HAS_STD_STRING_VIEW)

#if defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
# include <experimental/string_view>
#else // defined(BOOST_ASIO_HAS_EXPERIMENTAL_STRING_VIEW)
# include <string_view>
#endif // defined(BOOST_ASIO_HAS_EXPERIMENTAL_STRING_VIEW)
=======
#if defined(BOOST_ASIO_HAS_STRING_VIEW)

#if defined(BOOST_ASIO_HAS_STD_STRING_VIEW)
# include <string_view>
#elif defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
# include <experimental/string_view>
#else // defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
# error BOOST_ASIO_HAS_STRING_VIEW is set but no string_view is available
#endif // defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace asio {

<<<<<<< HEAD
#if defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
using std::experimental::basic_string_view;
using std::experimental::string_view;
#else // defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
using std::basic_string_view;
using std::string_view;
=======
#if defined(BOOST_ASIO_HAS_STD_STRING_VIEW)
using std::basic_string_view;
using std::string_view;
#elif defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)
using std::experimental::basic_string_view;
using std::experimental::string_view;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#endif // defined(BOOST_ASIO_HAS_STD_EXPERIMENTAL_STRING_VIEW)

} // namespace asio
} // namespace boost

# define BOOST_ASIO_STRING_VIEW_PARAM boost::asio::string_view
<<<<<<< HEAD
#else // defined(BOOST_ASIO_HAS_STD_STRING_VIEW)
# define BOOST_ASIO_STRING_VIEW_PARAM const std::string&
#endif // defined(BOOST_ASIO_HAS_STD_STRING_VIEW)
=======
#else // defined(BOOST_ASIO_HAS_STRING_VIEW)
# define BOOST_ASIO_STRING_VIEW_PARAM const std::string&
#endif // defined(BOOST_ASIO_HAS_STRING_VIEW)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif // BOOST_ASIO_DETAIL_STRING_VIEW_HPP
