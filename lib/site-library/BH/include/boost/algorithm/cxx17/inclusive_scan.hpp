/*
   Copyright (c) Marshall Clow 2017.

   Distributed under the Boost Software License, Version 1.0. (See accompanying
<<<<<<< HEAD
   file LICENSE10.txt or copy at http://www.boost.org/LICENSE10.txt)
=======
   file LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
*/

/// \file  transform_reduce.hpp
/// \brief Combine the (transformed) elements of a sequence (or two) into a single value.
/// \author Marshall Clow

#ifndef BOOST_ALGORITHM_TRANSFORM_REDUCE_HPP
#define BOOST_ALGORITHM_TRANSFORM_REDUCE_HPP

#include <functional>     // for std::plus
#include <iterator>       // for std::iterator_traits

<<<<<<< HEAD
=======
#include <boost/config.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
#include <boost/range/begin.hpp>
#include <boost/range/end.hpp>
#include <boost/range/value_type.hpp>

namespace boost { namespace algorithm {

template<class InputIterator, class OutputIterator, class T, class BinaryOperation>
OutputIterator inclusive_scan(InputIterator first, InputIterator last,
                              OutputIterator result, BinaryOperation bOp, T init)
{
    for (; first != last; ++first, (void) ++result) {
        init = bOp(init, *first);
        *result = init;
        }
    return result;
}


template<class InputIterator, class OutputIterator, class BinaryOperation>
OutputIterator inclusive_scan(InputIterator first, InputIterator last,
                              OutputIterator result, BinaryOperation bOp)
{
    if (first != last) {
        typename std::iterator_traits<InputIterator>::value_type init = *first;
        *result++ = init;
        if (++first != last)
<<<<<<< HEAD
            return inclusive_scan(first, last, result, bOp, init);
=======
            return boost::algorithm::inclusive_scan(first, last, result, bOp, init);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }

    return result;
}

template<class InputIterator, class OutputIterator>
OutputIterator inclusive_scan(InputIterator first, InputIterator last,
                   OutputIterator result)
{
    typedef typename std::iterator_traits<InputIterator>::value_type VT;
<<<<<<< HEAD
    return inclusive_scan(first, last, result, std::plus<VT>());
=======
    return boost::algorithm::inclusive_scan(first, last, result, std::plus<VT>());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
}

}} // namespace boost and algorithm

#endif // BOOST_ALGORITHM_TRANSFORM_REDUCE_HPP
