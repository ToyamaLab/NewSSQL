//
// detail/cstddef.hpp
// ~~~~~~~~~~~~~~~~~~
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

#ifndef BOOST_ASIO_DETAIL_CSTDDEF_HPP
#define BOOST_ASIO_DETAIL_CSTDDEF_HPP

#if defined(_MSC_VER) && (_MSC_VER >= 1200)
# pragma once
#endif // defined(_MSC_VER) && (_MSC_VER >= 1200)

#include <boost/asio/detail/config.hpp>
#include <cstddef>

namespace boost {
namespace asio {

#if defined(BOOST_ASIO_HAS_NULLPTR)
using std::nullptr_t;
#else // defined(BOOST_ASIO_HAS_NULLPTR)
struct nullptr_t {};
#endif // defined(BOOST_ASIO_HAS_NULLPTR)

} // namespace asio
} // namespace boost

#endif // BOOST_ASIO_DETAIL_CSTDDEF_HPP
