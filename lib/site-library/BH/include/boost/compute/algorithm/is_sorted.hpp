//---------------------------------------------------------------------------//
// Copyright (c) 2013-2014 Kyle Lutz <kyle.r.lutz@gmail.com>
//
// Distributed under the Boost Software License, Version 1.0
// See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt
//
// See http://boostorg.github.com/compute for more information.
//---------------------------------------------------------------------------//

#ifndef BOOST_COMPUTE_ALGORITHM_IS_SORTED_HPP
#define BOOST_COMPUTE_ALGORITHM_IS_SORTED_HPP

<<<<<<< HEAD
=======
#include <boost/static_assert.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/compute/command_queue.hpp>
#include <boost/compute/system.hpp>
#include <boost/compute/functional/bind.hpp>
#include <boost/compute/functional/operator.hpp>
#include <boost/compute/algorithm/adjacent_find.hpp>
<<<<<<< HEAD
=======
#include <boost/compute/type_traits/is_device_iterator.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace compute {

/// Returns \c true if the values in the range [\p first, \p last)
/// are in sorted order.
///
/// \param first first element in the range to check
/// \param last last element in the range to check
/// \param compare comparison function (by default \c less)
/// \param queue command queue to perform the operation
///
/// \return \c true if the range [\p first, \p last) is sorted
///
/// Space complexity: \Omega(1)
///
/// \see sort()
template<class InputIterator, class Compare>
inline bool is_sorted(InputIterator first,
                      InputIterator last,
                      Compare compare,
                      command_queue &queue = system::default_queue())
{
<<<<<<< HEAD
=======
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator>::value);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    using ::boost::compute::placeholders::_1;
    using ::boost::compute::placeholders::_2;

    return ::boost::compute::adjacent_find(
        first, last, ::boost::compute::bind(compare, _2, _1), queue
    ) == last;
}

/// \overload
template<class InputIterator>
inline bool is_sorted(InputIterator first,
                      InputIterator last,
                      command_queue &queue = system::default_queue())
{
<<<<<<< HEAD
=======
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator>::value);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef typename std::iterator_traits<InputIterator>::value_type value_type;

    return ::boost::compute::is_sorted(
        first, last, ::boost::compute::less<value_type>(), queue
    );
}

} // end compute namespace
} // end boost namespace

#endif // BOOST_COMPUTE_ALGORITHM_IS_SORTED_HPP
