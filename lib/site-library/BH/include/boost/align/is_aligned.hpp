/*
Copyright 2014 Glen Joseph Fernandes
(glenjofe@gmail.com)

Distributed under the Boost Software License, Version 1.0.
(http://www.boost.org/LICENSE_1_0.txt)

*/
#ifndef BOOST_ALIGN_IS_ALIGNED_HPP
#define BOOST_ALIGN_IS_ALIGNED_HPP

#include <boost/align/detail/is_aligned.hpp>
<<<<<<< HEAD
=======
#include <boost/align/detail/not_pointer.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace alignment {

<<<<<<< HEAD
BOOST_CONSTEXPR inline bool
is_aligned(std::size_t value, std::size_t alignment) BOOST_NOEXCEPT
{
    return (value & (alignment - 1)) == 0;
=======
template<class T>
BOOST_CONSTEXPR inline typename detail::not_pointer<T, bool>::type
is_aligned(T value, std::size_t alignment) BOOST_NOEXCEPT
{
    return (value & (T(alignment) - 1)) == 0;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

} /* alignment */
} /* boost */

#endif
