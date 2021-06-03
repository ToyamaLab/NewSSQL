//---------------------------------------------------------------------------//
// Copyright (c) 2013 Kyle Lutz <kyle.r.lutz@gmail.com>
//
// Distributed under the Boost Software License, Version 1.0
// See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt
//
// See http://boostorg.github.com/compute for more information.
//---------------------------------------------------------------------------//

#ifndef BOOST_COMPUTE_ALGORITHM_INNER_PRODUCT_HPP
#define BOOST_COMPUTE_ALGORITHM_INNER_PRODUCT_HPP

<<<<<<< HEAD
=======
#include <boost/static_assert.hpp>

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/compute/system.hpp>
#include <boost/compute/functional.hpp>
#include <boost/compute/command_queue.hpp>
#include <boost/compute/algorithm/accumulate.hpp>
#include <boost/compute/container/vector.hpp>
#include <boost/compute/iterator/transform_iterator.hpp>
#include <boost/compute/iterator/zip_iterator.hpp>
#include <boost/compute/functional/detail/unpack.hpp>
<<<<<<< HEAD
=======
#include <boost/compute/type_traits/is_device_iterator.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost {
namespace compute {

/// Returns the inner product of the elements in the range
/// [\p first1, \p last1) with the elements in the range beginning
/// at \p first2.
///
/// Space complexity: \Omega(1)<br>
/// Space complexity when binary operator is recognized as associative: \Omega(n)
template<class InputIterator1, class InputIterator2, class T>
inline T inner_product(InputIterator1 first1,
                       InputIterator1 last1,
                       InputIterator2 first2,
                       T init,
                       command_queue &queue = system::default_queue())
{
<<<<<<< HEAD
=======
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator1>::value);
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator2>::value);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef typename std::iterator_traits<InputIterator1>::value_type input_type;

    ptrdiff_t n = std::distance(first1, last1);

    return ::boost::compute::accumulate(
        ::boost::compute::make_transform_iterator(
            ::boost::compute::make_zip_iterator(
                boost::make_tuple(first1, first2)
            ),
            detail::unpack(multiplies<input_type>())
        ),
        ::boost::compute::make_transform_iterator(
            ::boost::compute::make_zip_iterator(
                boost::make_tuple(last1, first2 + n)
            ),
            detail::unpack(multiplies<input_type>())
        ),
        init,
        queue
    );
}

/// \overload
template<class InputIterator1,
         class InputIterator2,
         class T,
         class BinaryAccumulateFunction,
         class BinaryTransformFunction>
inline T inner_product(InputIterator1 first1,
                       InputIterator1 last1,
                       InputIterator2 first2,
                       T init,
                       BinaryAccumulateFunction accumulate_function,
                       BinaryTransformFunction transform_function,
                       command_queue &queue = system::default_queue())
{
<<<<<<< HEAD
=======
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator1>::value);
    BOOST_STATIC_ASSERT(is_device_iterator<InputIterator2>::value);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typedef typename std::iterator_traits<InputIterator1>::value_type value_type;

    size_t count = detail::iterator_range_size(first1, last1);
    vector<value_type> result(count, queue.get_context());
    transform(first1,
              last1,
              first2,
              result.begin(),
              transform_function,
              queue);

    return ::boost::compute::accumulate(result.begin(),
                                        result.end(),
                                        init,
                                        accumulate_function,
                                        queue);
}

} // end compute namespace
} // end boost namespace

#endif // BOOST_COMPUTE_ALGORITHM_INNER_PRODUCT_HPP
