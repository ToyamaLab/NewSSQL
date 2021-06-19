// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2015 Barend Gehrels, Amsterdam, the Netherlands.

<<<<<<< HEAD
=======
// This file was modified by Oracle on 2018.
// Modifications copyright (c) 2018, Oracle and/or its affiliates.
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_ALGORITHMS_DETAIL_SECTIONS_SECTION_BOX_POLICIES_HPP
#define BOOST_GEOMETRY_ALGORITHMS_DETAIL_SECTIONS_SECTION_BOX_POLICIES_HPP


#include <boost/geometry/algorithms/detail/disjoint/box_box.hpp>
#include <boost/geometry/algorithms/expand.hpp>


namespace boost { namespace geometry
{

#ifndef DOXYGEN_NO_DETAIL
namespace detail { namespace section
{

<<<<<<< HEAD
=======
template <typename ExpandBoxStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct get_section_box
{
    template <typename Box, typename Section>
    static inline void apply(Box& total, Section const& section)
    {
<<<<<<< HEAD
        geometry::expand(total, section.bounding_box);
    }
};

=======
        geometry::expand(total, section.bounding_box,
                         ExpandBoxStrategy());
    }
};

template <typename DisjointBoxBoxStrategy>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct overlaps_section_box
{
    template <typename Box, typename Section>
    static inline bool apply(Box const& box, Section const& section)
    {
<<<<<<< HEAD
        return ! detail::disjoint::disjoint_box_box(box, section.bounding_box);
=======
        return ! detail::disjoint::disjoint_box_box(box, section.bounding_box,
                                                    DisjointBoxBoxStrategy());
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }
};


}} // namespace detail::section
#endif


}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_ALGORITHMS_DETAIL_SECTIONS_SECTION_BOX_POLICIES_HPP
