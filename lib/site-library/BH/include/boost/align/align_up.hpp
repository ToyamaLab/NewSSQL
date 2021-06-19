/*
Copyright 2015 Glen Joseph Fernandes
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)
*/
#ifndef BOOST_ALIGN_ALIGN_UP_HPP
#define BOOST_ALIGN_ALIGN_UP_HPP

#include <boost/align/detail/align_up.hpp>
<<<<<<< HEAD
=======
#include <boost/align/detail/not_pointer.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace alignment {

<<<<<<< HEAD
BOOST_CONSTEXPR inline std::size_t
align_up(std::size_t value, std::size_t alignment) BOOST_NOEXCEPT
{
    return (value + alignment - 1) & ~(alignment - 1);
=======
template<class T>
BOOST_CONSTEXPR inline typename detail::not_pointer<T, T>::type
align_up(T value, std::size_t alignment) BOOST_NOEXCEPT
{
    return T((value + (T(alignment) - 1)) & ~T(alignment - 1));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} /* alignment */
} /* boost */

#endif
