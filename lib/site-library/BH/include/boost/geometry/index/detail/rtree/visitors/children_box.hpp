// Boost.Geometry Index
//
// R-tree node children box calculating visitor implementation
//
// Copyright (c) 2011-2015 Adam Wulkiewicz, Lodz, Poland.
//
<<<<<<< HEAD
=======
// This file was modified by Oracle on 2019.
// Modifications copyright (c) 2019 Oracle and/or its affiliates.
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle
//
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_CHILDREN_BOX_HPP
#define BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_CHILDREN_BOX_HPP

namespace boost { namespace geometry { namespace index {

namespace detail { namespace rtree { namespace visitors {

template <typename Value, typename Options, typename Translator, typename Box, typename Allocators>
class children_box
    : public rtree::visitor<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag, true>::type
{
<<<<<<< HEAD
    typedef typename rtree::internal_node<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag>::type internal_node;
    typedef typename rtree::leaf<Value, typename Options::parameters_type, Box, Allocators, typename Options::node_tag>::type leaf;

public:
    inline children_box(Box & result, Translator const& tr)
        : m_result(result), m_tr(tr)
=======
    typedef typename Options::parameters_type parameters_type;

    typedef typename rtree::internal_node<Value, parameters_type, Box, Allocators, typename Options::node_tag>::type internal_node;
    typedef typename rtree::leaf<Value, parameters_type, Box, Allocators, typename Options::node_tag>::type leaf;

public:
    inline children_box(Box & result, parameters_type const& parameters, Translator const& tr)
        : m_result(result), m_parameters(parameters), m_tr(tr)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    {}

    inline void operator()(internal_node const& n)
    {
        typedef typename rtree::elements_type<internal_node>::type elements_type;
        elements_type const& elements = rtree::elements(n);

<<<<<<< HEAD
        m_result = rtree::elements_box<Box>(elements.begin(), elements.end(), m_tr);
=======
        m_result = rtree::elements_box<Box>(elements.begin(), elements.end(), m_tr,
                                            index::detail::get_strategy(m_parameters));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

    inline void operator()(leaf const& n)
    {
        typedef typename rtree::elements_type<leaf>::type elements_type;
        elements_type const& elements = rtree::elements(n);

<<<<<<< HEAD
        m_result = rtree::values_box<Box>(elements.begin(), elements.end(), m_tr);
=======
        m_result = rtree::values_box<Box>(elements.begin(), elements.end(), m_tr,
                                          index::detail::get_strategy(m_parameters));
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    }

private:
    Box & m_result;
<<<<<<< HEAD
=======
    parameters_type const& m_parameters;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    Translator const& m_tr;
};

}}} // namespace detail::rtree::visitors

}}} // namespace boost::geometry::index

#endif // BOOST_GEOMETRY_INDEX_DETAIL_RTREE_VISITORS_CHILDREN_BOX_HPP
