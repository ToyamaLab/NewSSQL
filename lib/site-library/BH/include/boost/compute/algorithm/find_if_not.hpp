//---------------------------------------------------------------------------//
// Copyright (c) 2013 Kyle Lutz <kyle.r.lutz@gmail.com>
//
// Distributed under the Boost Software License, Version 1.0
// See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt
//
// See http://boostorg.github.com/compute for more information.
//---------------------------------------------------------------------------//

#ifndef BOOST_COMPUTE_ALGORITHM_FIND_IF_NOT_HPP
#define BOOST_COMPUTE_ALGORITHM_FIND_IF_NOT_HPP

<<<<<<< HEAD
=======
#include <boost/static_assert.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/compute/system.hpp>
#include <boost/compute/functional.hpp>
#include <boost/compute/command_queue.hpp>
#include <boost/compute/algorithm/find_if.hpp>
<<<<<<< HEAD
=======
#include <boost/compute/type_traits/is_device_iterator.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace compute {

/// Returns an iterator pointing to the first element in the range
/// [\p first, \p last) for which \p predicate returns \c false.
///
/// Space complexity: \Omega(1)
///
/// \see find_if()
template<class InputIterator, class UnaryPredicate>
inline InputIterator find_if_not(InputIterator first,
                                 InputIterator last,
                                 UnaryPredicate predicate,
                                 command_queue &queue = system::default_queue())
{
<<<<<<< HEAD
=======
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator>::value);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    return ::boost::compute::find_if(
               first,
               last,
               not1(predicate),
               queue
           );
}

} // end compute namespace
} // end boost namespace

#endif // BOOST_COMPUTE_ALGORITHM_FIND_IF_NOT_HPP
